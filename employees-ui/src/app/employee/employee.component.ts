import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ModalDismissReasons, NgbDate, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormGroup, NgForm} from "@angular/forms";


export class Employee {
  constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public birthday: Date,
    public position: string,
    public email: string
  ) {
  }
}

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employees: Employee[];
  closeResult: string;
  birthday: NgbDate;
  editForm: FormGroup;

  constructor(
    private httpClient: HttpClient,
    private modalService: NgbModal,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.getEmployees();
    this.editForm = this.formBuilder.group({
      id: [''],
      firstName: [''],
      lastName: [''],
      birthday: [''],
      position: [''],
      email: ['']
    } );
  }

  getEmployees() {
    this.httpClient.get<any>('http://localhost:8080/employees').subscribe(
      response => {
        console.log(response);
        this.employees = response;
      }
    );
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.dismissReason(reason)}`;
    });
  }

  private dismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  onSubmit(f: NgForm) {
    const url = 'http://localhost:8080/employees';
    f.value.birthday = new Date(f.value.birthday.year, f.value.birthday.month - 1, f.value.birthday.day + 1);
    this.httpClient.post(url, f.value)
      .subscribe((result) => {
        this.ngOnInit();
      });
    this.modalService.dismissAll();
  }

  openEdit(targetModal, employee: Employee) {
    this.modalService.open(targetModal, {
      centered: true,
      backdrop: 'static'
    });
    this.editForm.patchValue({
      id: employee.id,
      firstName: employee.firstName,
      lastName: employee.lastName,
      birthday: employee.birthday,
      position: employee.position,
      email: employee.email
    })
  }

  saveChanges() {
    const editUrl = 'http://localhost:8080/employees/' + this.editForm.value.id;
    console.log(this.editForm.value);
    this.httpClient.put(editUrl, this.editForm.value)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }

  onDelete(employee: Employee) {
    const deleteURL = 'http://localhost:8080/employees/' + employee.id;
    this.httpClient.delete(deleteURL)
      .subscribe((results) => {
        this.ngOnInit();
      });
  }
}
