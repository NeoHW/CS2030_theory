class Book {
    private final ImList<Page> pages;
    private final int pageNum;

    Book(ImList<Page> pages) {
        this(pages, 1);
        //this.pages = pages;
        //this.pageNum = 1;
    }

    Book(ImList<Page> pages, int pageNum) {
        this.pages = pages;
        this.pageNum = pageNum;
    }

    private int numOfPages() {
        return this.pages.size();
    }

    Book gotoPage(int newPageNum) {
        if (newPageNum < 1 || newPageNum > this.numOfPages()) {
            return this;
        } else {
            return new Book(this.pages, newPageNum);
        }
    }

    Book nextPage() {
        return this.gotoPage(this.pageNum + 1);
    }
    
    Book prevPage() {
        return this.gotoPage(this.pageNum - 1);
    }

    Page getCurrentPage() {
        return this.pages.get(this.pageNum - 1) ;
    }

    public String toString() {
        return "\n" + this.getCurrentPage() + "\n--" + this.pageNum + "--";
    }
}
