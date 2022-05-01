package de.htwberlin.kba.user_management.export;

public interface UserService {
    /**
     * Show the user a list of users without showing the user himself in the list.
     * @param userId of the current user
     * @return userList of all users except the current user
     */
    public List<User> getUserList(Long userId);

    /**
     * Let the user choose an opponent for a new game.
     * @param List of all users to be able to choose an opponent
     * @return userId of the chosen opponent
     */
    public Long chooseUser(List<User> users);

    /**
     * Calculates the total points of a user by adding up the points of every game he played so far
     * @return total points
     */
    public int calculatePoints();
}
