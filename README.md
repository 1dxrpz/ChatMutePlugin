# ChatMute Plugin
![logo](chat.jpg)

## Usage
```
/mute <player> <time> [reason]
```
<> - required options
[] - optional

<player> - player name
<time> - amount of time with prefixes
```
  d - days
  h - hours
  m - minutes
  s - seconds
  e.g /mute Player 1d0h30m - will mute for 1 day and 30 minutes
```
## Permissions
```
  chatmute.commands - grant permission to use mute/unmute commands
```
## config

Variables:
  {player} - target player
  {amount} - formated amount of time
  {executor} - /mute command executor
  {reason} - reason of mute

playerMutedInfo - information of mute for executor
playerMuteAnnounce - information of mute for player
enablePublicAnnouncement - send playerMutedInfo to all players

muteAnnounce - message when muted player tries to write
enableMuteAnnounce - toggle MuteAnnounce message
  
permission - edit permission to use mute/unmute commands
