package com.nikhilz.rest.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@RestController
class TasksController {
    @Autowired
    lateinit var taskRepository: TaskRepository


    @PostMapping("/task")
    fun createTask(@RequestBody task: Task): Task {
        return taskRepository.save(task)
    }

    @DeleteMapping("/task/{id}")
    fun deleteTask(@PathVariable id: Long) {
        taskRepository.deleteById(id)
    }
    @GetMapping("/task/{id}")
    fun getTask(@PathVariable id: Long): Task {
        return taskRepository.findById(id).orElseThrow { TaskNotFoundException(id) }
    }

    @GetMapping("/tasks")
    fun getTasks(): MutableIterable<Task> {
        return taskRepository.findAll()
    }

}

@ControllerAdvice
annotation class ControllerAdvice {
    companion object {
        @ExceptionHandler
        fun handleTaskNotFoundException(ex: TaskNotFoundException): ResponseEntity<ErrorMessage> {
            val errorMessage = ErrorMessage(
                ex.message, HttpStatus.NOT_FOUND.toString(),
            )
            return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
        }
    }
}

class ErrorMessage(
    var message: String?,
    var status: String
)
class TaskNotFoundException(id: Long) :
    Exception("Task with id = $id not found")

interface TaskRepository : CrudRepository<Task, Long>


@Entity
data class Task(
    @Id @GeneratedValue
    val id: Long,
    val name: String,
    val description: String,
    val complexity: Complexity
)

enum class Complexity {
    EASY, MEDIUM, HARD
}