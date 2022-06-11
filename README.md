# Training_Service_IPC_Java
This repository is meant as a training example for establishing IPC between two service. Therefore a meetingplanner service is checking the availability of attendees 
by communicating with a timeslot service. Both apps are realized as spring boot apis.

## scenario for meeting is possible
In order to determine whether a meeting is possible following steps are passed:
1.) The meetintplanner service is invoked by specifying time and attendees.
2.) For each attendee the meetingplanner queries the timeslot service for already reserved timeslots of the attendee.
3.) The meetingplaner verfies if all attendees are free by checking timeslots received from timeslot service.
4.) If and only if all attendees have time the meeting planner returns true for the ispossible property in it's response. Otherwise false.

## launch the system
Before you can watch the communication between the service you have to bring up both apps.

### launch timeslot service
Move to the folder ./Training_Service_IPC_Java/timeslot/timeslot and launch the spring boot api with "mvn spring-boot:run".

You can test the api with 
http://localhost:8081/meeting/plan/ispossible?attendees=1,2&from=2022-06-11T15:43&to=2022-06-11T18:53
and you will receive:
```JSON
[
  {
    "from":"2022-06-11T15:43:00",
    "to":"2022-06-11T16:43:00",
    "ownerId":1
  },
  {
    "from":"2022-06-11T17:43:00",
    "to":"2022-06-11T18:43:00",
    "ownerId":1
  }
]
```

### launch meetingplanner service
Move to the folder ./Training_Service_IPC_Java/meetingplanner/meetinplanner and lauch the spring boot api with "mvn spring-boot:run".

You can test the api with 
http://localhost:8081/meeting/plan/ispossible?attendees=1,2&from=2022-06-11T15:43&to=2022-06-11T18:53
and you will receive:
```JSON
{
  "possible":false
}
```

Please be aware that meetingplanner service depends on the timeslot service, so that the availability of the latter is required.
