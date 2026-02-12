export default class UserInfo {
  constructor(
    id,
    username,
    email,
    isAdmin,
    twits,
    firstname,
    lastname,
    joinDate = new Date(),
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.isAdmin = isAdmin;
    this.twits = twits || [];
    this.firstname = firstname;
    this.lastname = lastname;
    this.joinDate = joinDate;
  }

  static createDefaultUser() {
    return new UserInfo(
      1,
      "golda6",
      "",
      false,
      [
        {
          id: 2,
          content: "Facebook is amazing",
          postDate: new Date("2024-06-01T12:00:00"),
        },
      ],
      "avi",
      "gold",
    );
  }

  toLocaleDateString(date) {
    return new Date(date).toLocaleDateString();
  }
}
