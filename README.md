## Table of contents
* [General info](#general-info)
* [Usage informations](#usage-informations)
* [Packages](#packages)
## General info
Software engineering project: **Smart Maintenance App**

Team members:
* Marco Ammirati
* Rosanna Coccaro
* Grazia D'Amore
* Francesco Femia

## Usage informations
To run the project go into the folder `/src/GUI` launch the `login.java` 
* At the first opening of the program it is required to authenticate as **SystemAdmin** and it is possible to create new users ( *e.g. Planner, Maintainer* ), view them and modify maintainers only (in this release) by double clicking on them in the list. Finally it is possible to view all the activities, eventually searching for them by **ID** and it is possible to add new competences to them by double clicking.
* Subsequently, opening the log-in form it is possible to access as a **Planner** (if it was defined by the systemAdmin) and to create new activities, assign them to a **Maintainer** (if it was defined by the systemAdmin) according to his availability. It is also possible to view all the unassigned activities and delete them or modify their attributes such as: *WeekNum, Description, factorySite, Department* .
## Packages
There are three principale packages:
* `/src/Activity`: centred on the Maintenance Activities
* `/src/GUI`: relative to the presentation side
* `/src/users`: focused on the different type of users (systemAdmin, planner, maintainer)
