export default class NavItem {
    private name = "";
    private to = "";

    public get Name() {
        return this.name;
    }

    public get To() {
        return this.to;
    }

    public set Name(name) {
        this.name = name
    }

    public set To(to) {
        this.to = to;
    }
}