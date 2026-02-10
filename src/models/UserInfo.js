export default class UserInfo {
  constructor(id, username, email, isAdmin, twits, firstname, lastname) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.isAdmin = isAdmin;
    this.twits = twits || [];
    this.firstname = firstname;
    this.lastname = lastname;
  }

  static createDefaultUser() {
    return new UserInfo(
      1,
      "golda6",
      "",
      false,
      [{ id: 2, content: "Facebook is amazing" }],
      "avi",
      "gold",
    );
  }
}
