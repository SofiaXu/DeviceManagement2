export default class Pagination {
    private currentPage = 1;
    private pageSize = 5;
    private maxPage = 1;

    public get CurrentPage() {
        return this.currentPage;
    }

    public set CurrentPage(currentPage) {
        this.currentPage = currentPage;
    }

    public get PageSize() {
        return this.pageSize;
    }

    public set PageSize(pageSize) {
        this.pageSize = pageSize;
    }

    public get MaxPage() {
        return this.maxPage;
    }

    public set MaxPage(maxPage) {
        this.maxPage = maxPage;
    }
}