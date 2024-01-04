Android Wear Task Manager App

The project includes:
A screen for adding the task optimized for different watch shapes.
Task details such as Task Id, Task Name and Due Date & Time for each task.
Task Id unique for each task.
Voice input implemented for entering the task name.
Implemented a way to persistently store tasks (Hint: SharedPref), so they are not lost when the app is closed, or the device is restarted.
Showcased the list of each newly added task in a WearableRecyclerView.
Each RecyclerView item displays the Task Id, Task Name, and Due Date & Time.
Implemented a mechanism for triggering notifications for tasks that are due within the next one hour.
The Wearable device notification includes the Task ID, Task Name.
The notifications also include an action which will load a new screen.
This new screen shows all the tasks that are due within the next one hour.
