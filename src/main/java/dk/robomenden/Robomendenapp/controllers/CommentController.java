package dk.robomenden.Robomendenapp.controllers;

import dk.robomenden.Robomendenapp.models.Comments;
import dk.robomenden.Robomendenapp.models.Role;
import dk.robomenden.Robomendenapp.services.BuyerService;
import dk.robomenden.Robomendenapp.services.CommentService;
import dk.robomenden.Robomendenapp.services.TaskService;
import dk.robomenden.Robomendenapp.services.WinterTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final BuyerService buyerService;
    private final TaskService taskService;
    private final WinterTaskService winterTaskService;

    @Autowired
    public CommentController(CommentService commentService, TaskService taskService, BuyerService buyerService, WinterTaskService winterTaskService) {
        this.commentService = commentService;
        this.taskService = taskService;
        this.buyerService = buyerService;
        this.winterTaskService = winterTaskService;
    }

    //Метод для создания нового комментария
    //Method for creating a new comment
    @PostMapping()
    public String saveComment(@ModelAttribute("comment") String s, @RequestParam("id") String id) {
        Comments comments = new Comments();
        comments.setComment(s);
        //Присваиваем покупателя комментарию
        //Assigning a buyer to a comment
        comments.setComment_owner(buyerService.findById(Integer.parseInt(id)).get());
        //Сохраняем комментарий
        //Saving a comment
        commentService.save(comments);
        return "redirect:/buyer/" + Integer.parseInt(id) + "/editBuyer";
    }


    //Метод для создания комментария у task'а
    //Method for creating a comment on a task
    @PostMapping("/task")
    public String saveTaskComment(@ModelAttribute("comment") String s, @RequestParam("id") String id) {
        Comments comments = new Comments();
        comments.setComment(s);
        //Присваиваем task комментарию
        //Assigning a task to a comment
        comments.setTask(taskService.findById(Integer.parseInt(id)));
        //Сохраняем комментарий
        //Saving a comment
        commentService.save(comments);
        return "redirect:/task/" + Integer.parseInt(id) + "/editTask";
    }

    //Метод для создания комментария у winterTask'а
    //Method for creating a comment on a winterTask
    @PostMapping("/winterTask")
    public String saveWinterTaskComment(@ModelAttribute("comment") String s, @RequestParam("id") String id) {
        Comments comments = new Comments();
        comments.setComment(s);
        //Присваиваем winterTask комментарию
        //Assigning winterTask to a comment
        comments.setWinter_task_comments(winterTaskService.findById(Integer.parseInt(id)));
        //Сохраняем комментарий
        //Saving a comment
        commentService.save(comments);
        return "redirect:/winter/" + Integer.parseInt(id) + "/editTask";
    }


    //Метод для удаления комментариев buyer'а
    //A method for deleting buyer's comments
    @PostMapping("/{id}/deleteComment")
    public String deleteComment(@PathVariable("id") int id, @RequestParam("clientId") String client_id) {
        commentService.deleteById(id);
        return "redirect:/buyer/" + Integer.parseInt(client_id) + "/editBuyer";
    }

    //Метод для удаления комментария task'а
    //Method for deleting a task comment
    @PostMapping("/task/{id}/deleteComment")
    public String deleteTaskComment(@PathVariable("id") int id, @RequestParam("clientId") String client_id) {
        commentService.deleteById(id);
        return "redirect:/task/" + Integer.parseInt(client_id) + "/editTask";
    }


    //Метод для удаления комментария winterTask'а
    //Method for deleting a winterTask comment
    @PostMapping("/winter/{id}/deleteComment")
    public String deleteWinterTaskComment(@PathVariable("id") int id, @RequestParam("clientId") String client_id) {
        commentService.deleteById(id);
        return "redirect:/winter/" + Integer.parseInt(client_id) + "/editTask";
    }

    //Метод для сохранения состояние доступа к комментарию
    //A method to save the access status of a comment
    @PostMapping("/check")
    public String check(@RequestParam int id, @RequestParam Boolean isChecked) {

        Comments comments = commentService.findById(id).get();

        comments.setChecked(isChecked);
        commentService.edit(id, comments);

        return "redirect:/comments";
    }

}
