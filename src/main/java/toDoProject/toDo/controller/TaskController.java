package toDoProject.toDo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toDoProject.toDo.details.MemberUserDetails;
import toDoProject.toDo.domain.Member;
import toDoProject.toDo.domain.Task;
import toDoProject.toDo.dto.TaskDTO;
import toDoProject.toDo.service.MemberService;
import toDoProject.toDo.service.TaskService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/task")
public class TaskController {

    private final MemberService memberService;
    private final TaskService taskService;

    @GetMapping("")
    public String task(@AuthenticationPrincipal MemberUserDetails userDetails, Model model) {
        log.info("-------------------- name = " + userDetails.getUsername());
//        Member member = memberService.findOne(id);
//        model.addAttribute("member", member);
//        List<Task> tasks = member.getTasks();
//        model.addAttribute("tasks", tasks);
//        model.addAttribute("task", new Task());
        return "task/main";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute TaskDTO taskDTO, HttpSession httpSession) {
        taskDTO.setStartDate(LocalDate.now());
        taskDTO.setStartTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
        Long memberId = (Long) httpSession.getAttribute("id");
        Member member = memberService.findOne(memberId);
        taskDTO.setMember(member);
        taskService.join(taskDTO);
        return "redirect:/task";
    }

    @GetMapping("/info")
    public String info(@RequestParam Long id, Model model) {
        Task task = taskService.findOne(id);
        model.addAttribute("task", task);
        return "task/info";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Long id) {
        log.info("----------id = {} --------", id);
        taskService.delete(id);
        return "redirect:/task";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("task") TaskDTO taskDTO, @RequestParam Long id) {
        taskService.update(id, taskDTO);
        return "redirect:/task";
    }
}

