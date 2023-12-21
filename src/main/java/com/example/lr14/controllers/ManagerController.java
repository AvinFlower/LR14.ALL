package com.example.lr14.controllers;

import org.springframework.ui.Model;
import com.example.lr14.entities.Manager;
import com.example.lr14.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/managers")
public class ManagerController {
    private ManagerService managerService;

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }
    @GetMapping
    public String showManagersList(Model model) {
        Manager manager = new Manager();
        model.addAttribute("managers", managerService.getAllManagers());
        model.addAttribute("manager", manager);
        return "managers";
    }

    @PostMapping("/add")
    public String addManager(@ModelAttribute("manager") Manager manager) {
        managerService.add(manager);
        return "redirect:/managers";
    }

    @GetMapping("/show/{id}")
    public String showOneManager(Model model, @PathVariable(value = "id") Integer id) {
        Manager manager = managerService.getById(id);
        model.addAttribute("manager", manager);
        return "manager-information";
    }

    @GetMapping("/delete/{id}")
    public String deleteManagers(Model model, @PathVariable(value = "id") Integer id) {
        Manager manager = managerService.getById(id);
        managerService.delete(manager);
        return "redirect:/managers";
    }

    @GetMapping("/add")
    public String showAddManagerForm(Model model) {
        Manager manager = new Manager();
        model.addAttribute("managers", managerService.getAllManagers());
        model.addAttribute("manager", manager);
        return "add-edit";
    }

    @GetMapping("/filter")
    public String filterManagers(Model model,
                                      @RequestParam(value = "manager_name", required = false)String manager_name,
                                      @RequestParam(value = "manager_phone_num", required = false) String manager_phone_num,
                                      @RequestParam(value = "manager_email", required = false) String manager_email) {
        Manager manager = new Manager();
        model.addAttribute("managers", managerService.getAllManagers(manager_name, manager_phone_num, manager_email));
        model.addAttribute("manager", manager);
        model.addAttribute("manager_name", manager_name);
        model.addAttribute("manager_phone_num", manager_phone_num);
        model.addAttribute("manager_email", manager_email);
        return "managers";
    }

    @GetMapping("/edit/{id}")
    public String showEditManagerForm(Model model, @PathVariable(value = "id") Integer id) {
        Manager manager = managerService.getById(id);
        model.addAttribute("manager", manager);
        return "add-edit";
    }

    @PostMapping("/edit/update")
    public String updateManager(@ModelAttribute("manager") Manager updatedManager) {
        Manager manager = managerService.getById(updatedManager.getId());
        managerService.update(manager, updatedManager);
        return "redirect:/managers";
    }
}