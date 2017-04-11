# Score-Tracker
A board game score tracker app for Android.

This is being used to learn about Android development, so expect super-high-quality code here.

Planned features:
- Delete game from list
- Delete move row from game
- Settings menu
    - show words in all caps
    - default game settings
        - number of players
        - game name
    - night mode
    - sort order of games in game list
    - font size
- Improve game info on list of saved games
    - number of players
    - total score for each player
- Filter game list
    - game name
    - number of players
    - date
- Improve game layout with large number of players
    - horizontal scrolling? even on tablet things get cramped with 4 players in portrait mode
    - minimum spacing between columns, or vertical line to divide columns
- New game from existing (new game keeps game name and players, wipes scores, words, etc.)
- Clear all scores
- Move timer (opens in overlay, tap outside to return to game scoring screen)

Known bugs:
- Newly saved changes in a game don't immediately reflect in the game list, have to leave screen and reenter.