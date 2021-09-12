# Projects

## PackScheduler

This Program is designed to be used by school administrators to enroll a directory of students into the courses offered their course catalog. Students can use the catalog to build their own schedules. Faculty can manage what courses they are teaching and see which students are enrolled in their courses. Registrars can assign students and faculty to courses, add/remove students, faculty, and courses, and output student directories, faculty directories, and the course catalog to a text file.

To run: Run PackScheduler/src/edu/ncsu/csc216/pack_scheduler/ui/PackSchedulerGUI.java

The program opens to a login screen. Only students, faculty, and registrars can login to the system. Use the following override login.

userID: testId

Password: hunter2

Walk through the program with the following System Test Plan: PackScheduler/project-docs/CSC216_L10_BBTP

## Project1 (ServiceWolf)

This Program is designed to be used by a customer service site. Customers can submit work orders (incidents) and will be entered into a finite state machine that manages the status of the incident. Service Groups will be assigned by the FSM to help the customer with their issue. Incidents have a state of 'New' when they are created by the customer, 'In Progress' if their is someone assigned to resolve it, 'On Hold' if the incident is put on hold, 'Canceled' if the incident was canceled by the customer, or 'Resolved if the servicer was able to fix the issue for the customer.

To run: Run Project1/src/edu/ncsu/csc216/service_wolf/view/ui/ServiceWolfGUI.java

Walk through the program with the following System Test Plan: Project1/project-docs/STP_P1P2

## Project2 (WolfTasks)

This Program is designed for personal use. It is a dynamic to-do list and task manager. Users have a Notebook in which they can create Task Lists. In each Task List, users can add and prioritize their tasks by moving them up of down on the list. Once a task is completed, users can change their status from 'Active' to 'Complete'. There is a master list of all active tasks across all Task Lists. The program also keeps track of how many tasks the user has completed since the Notebook was created.

To run: Run Project2/src/edu/ncsu/csc216/wolf_tasks/view/gui/WolfTasksGUI.java

Walk through the program with the following System Test Plan: Project2/project-docs/STP_P2P2
