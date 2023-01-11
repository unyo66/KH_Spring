package com.bitstudy.app.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;


/* webEnvironment : 기본값 Mock. Mocking 한 웹 환경 구성. 
*  None : 웹 환경을 넣지 않아 테스트를 가볍게 할 수 있음.
*  classes ... : 설정 클래스 지정해서 더 가볍게
*  */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)
class PaginationServiceTest {

    private final PaginationService sut;
    public PaginationServiceTest(@Autowired PaginationService sut) {
        this.sut = sut;
    }
    
    @ParameterizedTest(name = "[{index}] 현재 페이지 : {0}, 총 페이지: {1} => {2}")
    /* @ParameterizedTest : 여러 argument 를 등록해놓고 한번에 여러번 돌릴 수 있는 기능을 가지고 있는 테스트 에너테이션
    *   그거 제외하면 @Test 와 같음.
    *
    *  파라미터테스트 사용시 테스트를 위해 들어가는 값이나 객체들이 필요한데 그걸 Source 라고 함
    *
    *   Source 종류 세가지
    *   1. ValueSource : 같은 타입의 여러가지 단순한 값(literal value) 들을 테스트할때 사용 (@ValueSource(ints =
    *   2. CsvSource : 콤마로 구분되는 값을 사용 (@CsvSource(
    *   3.
    * */

    @MethodSource /* 이게 있어야 Arguments 메서드 가져옴. */
    @DisplayName("현재 페이지 번호와 총 페이지 수를 주면 페이징 바 리스트 만들기")
    void givePageNumAndTotalPagesReturnBarList(int currentPageNumber, int totalPages, List<Integer> expected) {
        //Given

        //When
        List<Integer> actual = sut.getPaginationBarNumbers(currentPageNumber, totalPages);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> givePageNumAndTotalPagesReturnBarList() {
        return Stream.of(
                /**  */
                arguments(0, 10, List.of(0, 1, 2, 3, 4)),
                arguments(1, 10, List.of(0, 1, 2, 3, 4)),
                arguments(2, 10, List.of(0, 1, 2, 3, 4)),
                arguments(3, 10, List.of(1, 2, 3, 4, 5)),
                arguments(4, 10, List.of(2, 3, 4, 5, 6)),
                arguments(5, 10, List.of(3, 4, 5, 6, 7)),
                arguments(6, 10, List.of(4, 5, 6, 7, 8)),
                arguments(7, 10, List.of(5, 6, 7, 8, 9)),
                arguments(8, 10, List.of(6, 7, 8, 9)),
                arguments(9, 10, List.of(7, 8, 9))
        );
    }
}