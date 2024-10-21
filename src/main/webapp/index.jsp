<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Testing JSP</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	loadEmployees();
    	
    });
    
    
    function loadEmployees(){
   
   	$("#name").val("");
	$("#email_id").val("");
	$("#mobile_no").val("");

	 $("#edit").hide();
   	 $("#save").show();
   	
               	$.ajax({
                       type: "get",
                       url: "http://localhost:8084/demo2/getData",
                       dataType: 'json',//this is my servlet
                      // data: "input=" +$('#ip').val()+"&output="+$('#op').val(),
                       success: function(data){    
                       	 const tbody = $('#employeeTable tbody');
                            tbody.empty(); // Clear existing data
                           
                            $.each(data, function(index, employee) {
                                tbody.append(
                                    '<tr>'+
                                        '<td>'+employee.id+'</td>'+
                                        '<td>'+employee.name+'</td> '+
                                        ' <td>'+employee.mobileNo+'</td> '+
                                        '<td>'+employee.emailId+'</td> '+
                                        '<td> '+
                                           ' <button onclick="editEmployee('+employee.id+')">Edit</button> '+
                                           '<button onclick="deleteEmployee('+employee.id+')">Delete</button> '+
                                           '</td> '+
                                           ' </tr>');
                                
                     
               	 
                   });}
   });
               	
   	}
    
    function editEmployee(id){
      	
  $("#edit_id").val(id);
    	$.ajax({
            type: "POST",
            url: "http://localhost:8084/demo2/getDataById",
            dataType: 'json',//this is my servlet
            data: {
	            id: id
	        },
            success: function(data){    
            	 $("#save").hide();
                 $("#edit").show();
               
                 $.each(data, function(index, employee) {
                	
                	// ("#name").val(employee.name);
                	 document.getElementById('name').value = employee.name;
                	 document.getElementById('mobile_no').value = employee.mobileNo;
                	 document.getElementById('email_id').value = employee.emailId;
                	// document.getElementById('edit_id').value = employee.id;
                	
                 });
	                
                 }
	        
            
});
    	
    }
</script>
<script>

function submitForm(event){
		
	    event.preventDefault();
	   
	     const name = $('#name').val();
	     const mobile_no = $('#mobile_no').val();
	     const email_id = $('#email_id').val();
	   

	    $.ajax({
	    	type: 'POST',
	        url: 'http://localhost:8084/demo2/saveData',
	        data: {
	            name: name,
	            mobile_no: mobile_no,
	            email_id: email_id
	        },
	        success: function(response) {
	        	loadEmployees();
	        	
	           // location.reload();
	          //  $('#userForm')[0].reset(); // Clear the form
	        },
	        error: function() {
	            alert('Error saving user.');
	        }
	});
}

function editForm(event){
	
    event.preventDefault();
   
     const name = $('#name').val();
     const mobile_no = $('#mobile_no').val();
     const email_id = $('#email_id').val();
     const id = $('#edit_id').val();


    $.ajax({
    	type: 'POST',
        url: 'http://localhost:8084/demo2/editData',
        data: {
            name: name,
            mobile_no: mobile_no,
            email_id: email_id,
            id:id
        },
        success: function(response) {
        	//$("#userForm")[0].reset(); 
        	loadEmployees();
        	
           // location.reload();
           // Clear the form
        },
        error: function() {
            alert('Error Updating user.');
        }
});
}


function deleteEmployee(id){
	   alert("delete"+id);

    $.ajax({
    	type: 'POST',
        url: 'http://localhost:8084/demo2/deleteData',
        data: {
            id:id
        },
        success: function(response) {
        	loadEmployees();
           // location.reload();
          //  $('#userForm')[0].reset(); // Clear the form
        },
        error: function() {
            alert('Error Deleting user.');
        }
});
}



</script>
</head>
<body>
<h1>Form</h1>

    <form  id="userForm">
     <input type="hidden" id="edit_id"/>
    	<p>Name: <input type="text" id="name" /></p>
        <p>Mobile No: <input type="text" id="mobile_no" /></p>
        <p>Email Id: <input type="text" id="email_id" /></p>
        <p><input type="submit" value="Save" onclick="submitForm(event)" id="save"/> 
        <input type="submit" value="Edit" onclick="editForm(event)" id="edit"/> 
    <input type="reset" value="Reset" id="reset" /></p>
    </form>
    <div id="entitiesContainer"></div>
    
      <h1>Employee List</h1>
    <table id="employeeTable">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Mobile No</th>
                <th>Email ID</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Employee rows will be populated here -->
        </tbody>
    </table>
</body>

 

</html>