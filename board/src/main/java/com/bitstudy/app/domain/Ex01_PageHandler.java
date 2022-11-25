package com.bitstudy.app.domain;

public class Ex01_PageHandler {
    private int totalCount; // 전체 게시물 개수
    private int pageSize; // 한번에 보여줄 게시글 개수
    private int naviSize; // 한번에 보여줄 페이징 개수 (1 ~ 10 페이지)
    private int totalPage; // 전체 페이지 개수

    private int currPage; // 현재 페이지
    private int beginPage; // 현재 나타나는 네비게이션의 첫번째 페이지값
    private int endPage; // 현재 나타나는 네비게이션의 마지막 페이지값

    private boolean showPrev; // 이전 버튼 표시 여부
    private boolean showNext; // 다음 버튼 표시 여부

    // ############# TDD용 ##############

    // 페이징 계산할때 필요한거 : totalCount, currPage, pageSize
    public Ex01_PageHandler(int totalCount, int currPage, int pageSize){
        this.totalCount = totalCount;
        this.currPage = currPage;
        this.pageSize = pageSize;

        //전체 페이지 수 : 전체 게시글 수 / 한 페이지의 게시글 수
        //totalCount / pageSize가 int인 경우 소수점 자리가 버려질 수 있으므로 double 형변환 > 나누기 > 올림 > int 형변환
        totalPage = (int) Math.ceil(totalCount / (double)pageSize);
        //네비의 첫 페이지 : 현재 페이지 / 네비사이즈 * 네비사이즈 + 1
        beginPage = currPage / naviSize * naviSize + 1;
        //네비의 마지막 페이지 : 첫 페이지 + 네비사이즈 - 1
        //계산한 마지막 페이지보다 전체 페이지 수가 적을 경우 전체 페이지가 마지막 페이지가 되어야 함
        endPage = (beginPage + naviSize - 1) >= totalPage ? (beginPage + naviSize - 1) : totalPage;
        //이전/다음 페이지 버튼은 첫 페이지가 1일때 / 마지막 페이지가 전체 페이지와 같을때 안나옴
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }
    // TDD에서 임의로 전체 게시글수 몇개인지, 몇페이지 보고 있는지 가짜로 정보 보내서 저장
    public Ex01_PageHandler(int totalCount, int currPage) {
        this(totalCount, currPage, 10);
    }

    // TDD에서 호출할때 페이지 네비게이션 출력하는 부분
    void print() {
        System.out.print(showPrev ? "이전" : "");
        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.print(showNext ? "다음" : "");
    }

    @Override
    public String toString() {
        return "Ex01_PageHandler{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", currPage=" + currPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }
}
