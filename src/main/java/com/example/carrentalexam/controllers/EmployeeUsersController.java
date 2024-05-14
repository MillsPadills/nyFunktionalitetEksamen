package com.example.carrentalexam.controllers;

import com.example.carrentalexam.enums.CarStatus;
import com.example.carrentalexam.enums.EmployeeUserDepartment;
import com.example.carrentalexam.models.*;
import com.example.carrentalexam.services.CarService;
import com.example.carrentalexam.services.DamageService;
import com.example.carrentalexam.services.EmployeeUserService;
import com.example.carrentalexam.services.RentalContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeUsersController {

    private final EmployeeUserService employeeUserService;
    private final CarService carService;
    private final RentalContractService rentalContractService;
    private final DamageService damageService;

    // Som patty sagde til vores fremlæggelse er det vigtigt at vi bruger konstruktør i stedet for autowired for at sikre immutabilitet.
    public EmployeeUsersController(EmployeeUserService employeeUserService, CarService carService,
                                   RentalContractService rentalContractService, DamageService damageService) {
        this.employeeUserService = employeeUserService;
        this.carService = carService;
        this.rentalContractService = rentalContractService; // NY
        this.damageService = damageService;
    }

    @PostMapping("/login")
    public String logIn(@RequestParam String username, @RequestParam String password,
                        Model model) {
        List<EmployeeUser> employeeUsers = employeeUserService.getAllEmployees();
        for (int i = 0; i < employeeUsers.size(); i++) {
            if (employeeUsers.get(i).getUsername().equals(username)) {
                if (employeeUsers.get(i).getPassword().equals(password)) {
                    int employeeId = employeeUsers.get(i).getEmployeeUserId();
                    model.addAttribute(employeeUserService.getEmployee(employeeId));
                    return "home/mainMenu";
                }
                else {
                    model.addAttribute("WrongPassword", "Wrong password");
                    return "home/index";
                }
            }
        }
        model.addAttribute("UserDoesNotExist", "user does not exist");
        return "home/index";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "home/signIn";
    }

    @PostMapping("/signInAction")
    public String signInAction(@RequestParam String username, @RequestParam String password,
                               @RequestParam EmployeeUserDepartment employeeUserDepartment) {
        employeeUserService.addEmployee(username, password, employeeUserDepartment);
        return "redirect:/";
    }

    @GetMapping("/mainMenuDataRegistration")
    public String dataRegistration(@RequestParam int employeeUserId, Model model) {
        model.addAttribute(employeeUserService.getEmployee(employeeUserId)); // for at få brugerens navn til overskriften
        model.addAttribute(carService.getAllCars()); // Giver medarbejderen overblik over alle registrerede biler
        return "home/mainMenuDataRegistration";
    }

    @GetMapping("/mainMenuDamageAndRepair")
    public String damageAndRepair(@RequestParam int employeeUserId, Model model) {
        List<RentalContract> rentalContractsReturned = rentalContractService.getAllRentalContractWhereTheCarHasBeenReturned();
        List<CarWithContract> carsFromRentalContractsReturned = new ArrayList<>();

        for (RentalContract contract : rentalContractsReturned) {
            Car car = carService.getCar(contract.getCarId());
            if (car != null) {
                carsFromRentalContractsReturned.add(new CarWithContract(car, contract.getRentalContractId()));
            }
        }

        List<RentalContract> allRentalContracts = rentalContractService.getAllRentalContracts();
        List<CarWithContract> carsInMaintenanceWithDamages = new ArrayList<>();

        for (RentalContract contract : allRentalContracts) {
            Car car = carService.getCarMaintenance(contract.getCarId());
            if (car != null) {
                List<Damage> damages = damageService.getDamagesByContractId(contract.getRentalContractId());
                carsInMaintenanceWithDamages.add(new CarWithContract(car, contract.getRentalContractId(), damages));
            }
        }





        model.addAttribute("rentalContractCarsReturned", carsFromRentalContractsReturned);
        model.addAttribute("carsInMaintenanceWithDamages", carsInMaintenanceWithDamages);
        model.addAttribute("employeeUserId", employeeUserId);

        return "home/mainMenuDamageAndRepair";
    }




    @GetMapping("/mainMenuBusinessDeveloper") // NY
    public String businessDeveloper(@RequestParam int employeeUserId, Model model) {
        model.addAttribute(employeeUserService.getEmployee(employeeUserId));
        model.addAttribute("employeeUserId", employeeUserId);
        // int rentedCarsCount = rentalContractService.getRentedCarsCount();
        List<RentalContract> rentedCars = rentalContractService.getRentedCarsCount();
        double totalRevenue = rentalContractService.getTotalRevenue();
        model.addAttribute("rentedCarsCount", rentedCars.size());
        model.addAttribute("totalRevenue", totalRevenue);
        return "home/mainMenuBusinessDeveloper";
    }

}
