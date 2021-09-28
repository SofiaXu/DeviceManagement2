import { UserType } from "./UserType";

export class User {
    public userId = 0;
    public userName = "";
    public userRealName = "";
    public userAge = 0;
    public userGender = 0;
    public userType: UserType = new UserType();
}
