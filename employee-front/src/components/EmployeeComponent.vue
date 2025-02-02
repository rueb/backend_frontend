<script>
    import { Modal } from 'bootstrap/dist/js/bootstrap.bundle.min';
    import EmployeeService from '@/services/EmployeeService';

export default{
    data(){
        return{
            employees:[],
            employeeForm:{
                id: null,
                firstName:'',
                lastName:'',
                email:''
            },
            editing: false,
            modalinstance:null
        };
    },
    computed:{
        modalTitle(){
            return this.editing ? 'Edit Employee':'Add Employee';
        },
        buttonLabel(){
            return this.editing ? 'Update':'Save';
        }
    },
    methods:{
        showModal(employee){
            if(employee){
                this.employeeForm = {...employee};
                this.editing= true;
            }else{
                // REST
                this.restForm();
            }
            if(!this.modalinstance){
                this.modalinstance = new Modal(document.getElementById('employeeModal'));
            } 
            this.modalinstance.show();
        },
        saveEmployee(){
            const action = this.editing ? 'updateEmployee' : 'addEmployee';
            EmployeeService[action](this.employeeForm).then(()=>{
                this.loadEmployees();
                this.modalinstance.hide();
            });
        },
        loadEmployees(){
            EmployeeService.getEmployees().then(
                response => {
                    this.employees = response.data;
                });
        },
        deleteEmployee(id){
            EmployeeService.deleteEmployee(id).then(this.loadEmployees);
        },
        restForm(){
            this.employeeForm={
                id:null,
                firstName:'',
                lastName:'',
                email:''
            };
            this.editing = false;
    },
    },   
        mounted() {
            this.loadEmployees();
        }
}

  
    
</script>

<template>
    <div class="container mt-3">
        <button @click="showModal(null)" class="btn btn-primary">
            Add Employee
        </button>
        <table class="table table-stripped mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="employee in employees" :key="employee.id">
                    <td>{{ employee.id }}</td>
                    <td>{{ employee.firstName }}</td>
                    <td>{{ employee.lastName }}</td>
                    <td>{{ employee.email }}</td>
                    <td> <button @click="showModal(employee)" class="btn btn-warning">Edit</button><span></span>
                        <button class="btn btn-danger" @click="deleteEmployee(employee.id)">Delete</button>
                    </td>    
                </tr>
            </tbody>
        </table>
        <div class="modal fade" id="employeeModal" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"> {{ modalTitle }} </h5>
                        <button class="btn-close" type="button" data-bs-dismiss="modal"></button>
                    </div> 
                <div class="modal-body">
                    <form @submit.prevent="saveEmployee" >
                        <div class="mb-3">
                            <label class="form-label">First Name</label>
                            <input type="text"  class="form-control" v-model="employeeForm.firstName" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Last Name</label>
                            <input type="text"  class="form-control" v-model="employeeForm.lastName" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="text"  class="form-control" v-model="employeeForm.email" required>
                        </div>
                            <button type="submit" class="btn btn-primary">{{ buttonLabel }}</button>
                    </form>
                </div>
            </div>
            </div>
        </div>
    </div> 

</template>