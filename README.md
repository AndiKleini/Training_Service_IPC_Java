# Training_Service_IPC_Java
This repository is meant as a training example for establishing IPC between two service. Therefore a meetingplanner service is checking the availability of attendees 
by communicating with a timeslot service. Both apps are realized as psring boot apis.

## launch the system

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
