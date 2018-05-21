# Resetting the database
The files in this folder are meant to help set up a derivate of an existing CoLab instance by preserving some of its configuration and content but resetting other parts.

## Resetting everything

Simply run `reset-all.sql`.

## Resetting individual parts

Each script resets a particular sub-part of the system.

`reset-content`: this script removes all content created by users as well as contests. This is mainly proposals, comments and messages. About pages, wiki pages, schedules, and templates will be preserved.

`reset-tracking`:  this scrip removes all user tracking, but leaves user accounts intact.
