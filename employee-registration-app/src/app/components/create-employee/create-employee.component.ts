import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Form, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Department } from 'src/app/models/department.model';
import { EmployeeService } from 'src/app/services/employee.service';
import { Employee } from '../../models/employee.model';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee : Employee;
  submitted = false;
  departments : Department[];
  isAddMode: boolean;
  empId : number;
  code : string = "0";
  datePickerConfig : any;
  datePipe = new DatePipe('en-US');
  message = "You submitted successfully!";

  @ViewChild("f") form : FormGroup;

  constructor(private employeeService: EmployeeService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    
    this.datePickerConfig = {
      format : 'DD/MM/YYYY',
      drops: 'down'
    }
    this.empId = this.route.snapshot.params['id'];
    this.isAddMode = !this.empId;


    if(this.empId){
      this.employeeService.getEmployee(this.empId).subscribe(emp => {
        this.employee = emp;
        this.code = emp.deptCode;
        emp.doj = this.datePipe.transform(emp.doj, 'dd/MM/yyyy');
      })
    }
    else{
      this.employee = new Employee();
    }

    this.employeeService.getDepartments().subscribe((departments : Department[]) => {
      this.departments = departments;
    })
  }

  newEmployee(): void {
    this.submitted = false;
    this.employee = new Employee();
    this.form.reset();
  }

  isNumber(event : KeyboardEvent){
    
    var charCode = event.keyCode;
    if ((charCode < 48 || charCode > 57)) {
      event.preventDefault();
      return false;
    } else {
      return true;
    }
  }

  saveEmployee() {

    this.employee.deptCode = this.code; 
    this.employeeService.createEmployee(this.employee).subscribe((data : Employee) => {
      this.employee = data;
      this.gotoList();
    }, 
    error => {this.message = error.error}
    );
  }

  updateEmployee() {

    this.employee.deptCode = this.code;
    let dateParts = this.employee.doj.split("/");
    this.employee.doj = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);

    this.employeeService.updateEmployee(this.empId,this.employee).subscribe((data : Employee) => {
      this.employee = data;
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;

    if(this.isAddMode)
    this.saveEmployee();  
    else
    this.updateEmployee();
    
  }

  gotoList() {
    this.router.navigate(['/employees']);
  }

}
