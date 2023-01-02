package com.bitstudy.app.repository;
import com.bitstudy.app.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import com.bitstudy.app.config.JpaConfig;
import static org.assertj.core.api.Assertions.*;



import java.util.List;

// 슬라이드 테스트(유닛 테스트) : 메서드들 각각 테스트한 결과를 서로 못보게 잘라서 만드는것
@DataJpaTest

@Import(JpaConfig.class)
class JpaRepositoryTest {

    //원래는 둘다 @Autowired가 붙어야 하는데, Junit5와 최신 버전의 스프링 부트를 이용하면 Test에서 생성자 주입 패턴을 사용할 수 있음.
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
//    @Autowired
//    private ArticleRepository articleRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository, @Autowired CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    //트랜잭션 시 사용하는 메서드
    //사용법 : repository명.메서드
    //메서드 종류
    //  1) findAll() : 모든 컬럼 조회, 페이징 가능, select 전에 최신 데이터를 잡기 위해 update부터 함.
    //  2) findById() : 한 건에 대한 데이터 조회 시 사용(PK)
    //  3) save() : 레코드 저장할 때 (insert, update)
    //  4) count() : 레코드 갯수 뽑을때 사용
    //  5) delete() : 레코드 삭제


    //테스트용 데이터 가져오기
    //  1) mockroo 접속 (더미데이터 생성 사이트)
    //  2)

    //select 테스트
    @DisplayName("select 테스트")
    @Test
    void selectTest() {
        //select 할거니까 articleRepository를 기준으로 테스트할거임
        List<Article> newArticles =  articleRepository.findAll();

        //assertJ(assertThat)을 이용해 테스트할거임
        //articles 가 NotNull 이고 Size가 ~개면 통과
        assertThat(newArticles).isNotNull().hasSize(100);
    }

    @DisplayName("insert 테스트")
    @Test
    void insertTest() {
        //기존꺼 카운트
        long prevCount = articleRepository.count();

        //insert 하기
        Article newArticle = Article.of("hi", "hi", "hi");
        articleRepository.save(newArticle);

        //기존꺼랑 현재꺼 갯수 차이 구하기
        assertThat(articleRepository.count()).isEqualTo(prevCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void updateTest() {
        Article newArticle = articleRepository.findById(1L).orElseThrow();
        String newHashtag = "#newHashtag";
        newArticle.setHashtag(newHashtag);
        //save 만으로 돌리면 어차피 롤백될거니까 코드 유효성만 확인하고 update를 실제로 돌리지 않음.
        //그래서 saveAndFlush를 해줘야 함.
        //flush 란(push 같은거)
        //변경점 감지 -> 수정된Entity를 sql 저장소에 등록 -> sql 저장소에 있는 쿼리를 DB에 전송
        Article savedArticle = articleRepository.saveAndFlush(newArticle);
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", newHashtag);
//        assertThat(savedArticle.getHashtag()).isEqualTo(newHashtag);
    }

    @DisplayName("delete 테스트")
    @Test
    void deleteTest() {
        Article article = articleRepository.findById(1L).orElseThrow();
        // 1. 기존 갯수 구해서 변수에 넣기
        long prevCount = articleRepository.count();
        long prevCommentCount = commentRepository.count();
        int deletedComment = article.getComments().size();
        // 2. 지우기
        articleRepository.deleteById(1L);
        // 3. 지금 갯수랑 예전 갯수 -1이 같은지 비교
        assertThat(articleRepository.count()).isEqualTo(prevCount - 1);

        assertThat(commentRepository.count()).isEqualTo(prevCommentCount - deletedComment);
    }
}