# PlutoApp API

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.15.

## Development server

Run `npm install` and then `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

Staging server: ``http://ec2-18-216-78-140.us-east-2.compute.amazonaws.com``<br>
IP: ``18.216.78.140``
<br>
###Index
<ul>
	<li>1 - 4 Authentionation</li>
	<li>5 - 11 User Management</li>
	<li>12 - 16 Groups Management</li>
	<li>17 - 21 Object Type Management (also check #33 for list)</li>
	<li>22 - 26 Relationships</li>
	<li>27 - 33 Attributes Management</li>
	<li>34, 36, 42, 44 Model Management</li>
	<li>35 - 45 Model Viewer</li>
</ul>
<hr>

# API CALL DETAILS

## 1. Login: POST /api/login

When username provided, behind scenes also check for email login too, so we can login with both username and email

Request

```
	{
		username: 'Username or email',
		password: 'some password'
	}	
```

Response On Success

```
	{
	  status: true|false;
	  user: {
	  	name : "John Doe",
	  	login_key: "Special token string generated on a server side"
	  },
	  error : ""
	}	
```

Response On Failure

```
	{
		status: false,
		error: "Error string from the server"
	}	
```


## 2. Forgot Password: POST /api/forgot/password

On success, we send out an email with instructions to the user's provided email. The link should be in this format
{HOST}/auth/reset-password/{password_recover_token}

Request

```
	{
		email: <string>
	}	
```

Response On Success

```
	{
	  status: true;
	  error : ""
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 3. Reset Password: POST /api/reset/password

Request

```
	{
		password: 'some password',
		code: "(the code received from #2)"
	}	
```

Response On Success

```
	{
	  status: true <boolean>,
	  error : ""
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 4. Register: POST /api/register

Request

```
	{
		username: 'John Doe',
		email: 'a@b.com',
		password: '1234'
	}	
```

Response On Success

```
	{
	  status: true,
	  user : {
	  	name: "User Name",
	  	login_key : "1234567890" <special login key generated>
	  },
	  error : ""
	}	
```

Response On Failure

```
	{
		status: false,
		error: "(error string from the server)"
	}	
```
<hr>

## 5. List Of User: POST /users

Request

```
	{
		login_key: (logged in user token),
		page: 1 (this could be used for the pagination)
	}	
```

Response On Success

```
	{
		status: true,
		users: [
			{
				id: 1,
				name: "John Doe",
				username: "john_doe",
				email: "a@b.com",
				last_login_date: "(d/m/Y H:i format)"
			},
			...
		],
		pages: 20 <example of total pages>
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 6. Add User: POST /create/user

Request

```
	{
		form_data: {
			name : "John Doe",
			usenrame: "john_doe"
			email : "a@b.com",
			password : "1234"
		}
		groups: [id1, id2] optional,
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true,
		id: 1 "Id of new created user"
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 7. Edit User: POST /update/user

Request

```
	{
		login_key: "logged in user token",
		id: 1
		form_data : {
			name: "John Doe",
			username: "john_doe",
			email: "a@b.com",
		},
		groups: [1, 2] //one or multiple ids (optional)
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 8. Update Password: POST /update/user/password

Request

```
	{
		login_key: (logged in user token)
		id: 1,
		password: "1234", 
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 9. Delete User: POST /delete/user

Request

```
	{
		user_ids: [1, 2] (one or multiple user ids),
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true,
		pages: 2 (how may pages remaining, recalculate after deleting)
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 10. User Details: POST /user/details

Request

```
	{
		id: 1,
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true,
		user: {
			id: 1,
			name: "John Doe",
			email: "a@b.com",
			username: "john_doe"
			available_groups: [ 
				#All available groups including selected ones
				{
					id: 1,
					name: "Group1"
				},
				{
					id: 2,
					name: "Group2"
				},
				...
			],
			selected_groups: [
				{
					id: 1,
					name: "Group1"
				},
				...
			],
		}
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 11. User Available Groups: POST /user/available/groups

Request

```
	{
		login_key: "logged in user token"
	}	
```

Response On Success

```
	status: true,
	error: "",
	groups: [
		{
			id: 1,
			name: "Group 1",
			users: "4"
		},
		...
	]
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 12. List Of Groups: POST /groups

Request

```
	{
		login_key: "logged in user token",
		page: 1 "this could be used for the pagination"
	}	
```

Response On Success

```
	{
		status: true <boolean>,
		groups: [
			{
				id: <number> (group id),
				name: <string> (group name)
				group_users: <number> (total amount of users in group)
			},
			...
		],
		pages: 20 <total pages>
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 13. Add Group: POST /create/group

Request

```
	{
		name: "Group 1",
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 14. Edit Group: POST /update/group

Request

```
	{
		id: 1,
		name: "Group 1",
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 15. Delete Group: POST /delete/group

Request

```
	{
		group_ids: [1, 2] (one or multiple group ids),
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true,
		pages: 10 (total pages remainig after deleting items)
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 16. Group Details: POST /group/details

Request

```
	{
		id: 1,
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true,
		id: 1,
		name: "Group 1",
		total_users: 4 (total amount of users in group),
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>


#Metamodel Management

## 17. List Of Object Types: POST /object/types

Request

```
	{
		login_key: "logged in user token",
		sort: "name_asc|name_desc|created_date_asc|created_date_desc|updated_by_asc|updated_by_desc|updated_date_asc|updated_date_desc",
		page: 1 (this could be used for the pagination)
	}	
```

Response On Success

```
	{
		status: true <boolean>,
		object_types: [
			{
				id: 1,
				name: "Object Type 1",
				total_attributes: 4,
				created_date: "Y-m-d H:i:s" (date or empty/null),
			created_by: "user name who created the record",
			updated_by: "user name who modified model/folder or it's attributes last time",
			updated_date: "Y-m-d H:i:s" (date or empty/null),
			},
			...
		],
		pages: 20 total pages found
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>


## 18. Add Object Type: POST /create/object/type

Request

```
	{
		name: "Object Type 1",
		attribute_type_ids: [1, 2] (optional)
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true,
		page: 4
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 19. Edit Object Type: POST /update/object/type

Request

```
	{
		id: 1,
		login_key: "logged in user token",
		name: "Object type 1",
		attribute_type_ids: [1, 2] optional
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 20. Delete Object Type: POST /delete/object/type

Request

```
	{
		object_type_ids: [1, 2] (one or multiple object type ids),
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true,
		pages: 1, (pages remaining after deleted items)
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
## 21. Object Type Details: POST /object/type/details

Request

```
	{
		id: 1,
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true <boolean>,
		object_data: {
			id: 1,
			name: "Object type 1",
			available_attribute_types: [
				{
					id : 1,
					name: "Attribute 1"
				},
				...
			],
			selected_attribute_types: [
				{
					id : 1,
					name: "Attribute 1"
				},
				...
			]
		}
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>


## 22. List Of Relationship Types: POST /relationship/types

Request

```
	{
		login_key: "logged in user token",
		sort: 'name_asc|name_desc|from_to_asc|from_to_desc|to_from_asc|to_from_desc',
		page: 1 "this could be used for the pagination"
	}	
```

Response On Success

```
	{
		status: true <boolean>,
		relationship_types: [
			{
				id: 1,
				name: "Relationship 1",
				from_to_description: "From A",
				to_from_description: "To B"
			},
			...
		],
		pages: 20 <total pages>
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 23. Add Relationship Type: POST /create/relationship/type

Request

```
	{
		login_key: "logged in user token",
		form_data: {
			name: "Relationship1",
			from_to_description: "From A",
			to_from_description: "To B",
		],
		combinations: [
			{
				from_object_type_id: "1",
				to_object_type_id: "2",
				description: "Some description"
			},
			...
		]
	}	
```

Response On Success

```
	{
		status: true,
		pages: 1
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 24. Update Relationship Type: POST /update/relationship/type

Request

```
	{
		login_key: "logged in user token",
		form_data: {
			id: 'relationship type id',
			name: "Relationship1",
			from_to_description: "From A",
			to_from_description: "To B",
		],
		combinations: [
			{
				id: "either number for update or empty assuming new one"
				from_object_type_id: "1",
				to_object_type_id: "2",
				description: "Some description"
			},
			...
		]
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 25. Relationship Type Details: POST /relationship/type/details

Request

```
	{
		id: 1,
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true <boolean>,
		relationship_data: {
			id: "1",
			name: "Relationship type 1",
			from_to_description: "from A",
			to_from_description: "from B",
			combinations: [
				{
					id: 1,
					description: "some description",
					from_object_type: {
						id : "2",
						name: "From A"
					},
					to_object_type: {
						id : "2",
						name: "To B"
					}
				},
				...
			]
		}
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 26. Delete Relationship Type: DELETE /delete/relationship/type

Request

```
	{
		id: [1, 2] (one or multiple relationship type ids)
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true,
		pages: 1 (pages after delete)
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## Attribute Types

Notes: Each attribute type might have different formats.<br>
- Text<br>
- Date<br>
- Decimal<br>
- Integer<br>
- List <br>
- Multi-Select List<br>
- Booelan <br>

<B>Help Guide</b><br>

`attribute_type` table is going to have following fields<br>
- id<br>
- name <br>
- format_type <enum from above items <br>

This means we need multiple tables to link with<br>
`attribute_items` where we store date, decimal, integer, and text, boolean<br>
`attribute_list_item` where we store list items<br>

Good approach is having all types separated with unique tables but this is going to make performance a bit slower. Full list attribute keys are <br>

`text`, `date`, `integer`, `decimal`, `boolean`, `list`, `multiple-list`


## 27. List Of Attribute Types: POST /attribute/types

Request

```
	{
		login_key: "logged in user token",
		sort: "name_asc|name_desc|format_asc|format_desc",
		page: 1 "this could be used for the pagination"
	}	
```

Response On Success

```
	{
		status: true <boolean>,
		attribute_types: [
			{
				id: 1,
				name: "Text Attribute"
				format_label: ("Text", "Date", "List", ...)
			},
			...
		],
		pages: 20 <total pages>
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 28. Delete Attribute Types: POST /delete/attribute/type

Request

```
	{
		login_key: "logged in user token",
		attribute_type_ids: [1, 2] array containing one or multiple items
	}	
```

Response On Success

```
	{
		status: true,
		pages: 20, pages after deleting
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 29. Create Attribute Type: POST /attribute/type/create

Request

```
	{
		login_key: "logged in user token",
		form_data: {
			name : "Attribute Type 1",
			format: text|date|decimal|integer|boolean|list|multiple-list,
			object_type_ids: [1, 2,...] optional one or multiple in array,
			list_options : ['item1','item2',...] only when format is ``list`` or ``multiple-list``,
			number_options: {
				from_number: 10, (for decimal is decimal) optional
				to_number: 20, (for decimal is decimal) optional
				currency: 'EUR' (optional),
				decimal_places: 1 (optional and required for decimal type only)
			},
			boolean_options: {
				default : 'true' | 'false' | null (when not selected null is assigned)
			}
			
		}
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 30. Get Attribute Details: POST /attribute/type/detail

Request

```
	{
		login_key: "logged in user token",
		id: 1
	}	
```

Response On Success

```
	{
	status: true,
    error: '',
    attribute_data : {
      id: 1,
      name: 'Dummy Attribute',
	  format: text|date|decimal|integer|boolean|list|multiple-list,
      available_object_types: [
        {
          id: '1',
          name: 'Object Type 1'
        },
        {
          id: '2',
          name: 'Object Type 2'
        },
        {
          id: '3',
          name: 'Object Type 3'
        },
        {
          id: '4',
          name: 'Object Type 4'
        }
      ],
      selected_object_types : [
        {
          id: '1',
          name: 'Object Type 1'
        },
        {
          id: '2',
          name: 'Object Type 2'
        },
        {
          id: '3',
          name: 'Object Type 3'
        },
      ],
      list_options: [],
      number_options: {
        'from_number' : 10,
        'to_number' : 20,
        'currency' : 'USD',
        'decimal_places' : 1
      },
      boolean_options: {
        'default' : 'true'
      }
    }
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>



## 31. Update Attribute Type: POST /attribute/type/update

Request

```
	{
		login_key: "logged in user token",
		form_data: {
			id: "1",
			name : "Attribute Type 1",
			format: text|date|decimal|integer|boolean|list|multiple-list,
			object_type_ids: [1, 2,...] optional one or multiple in array,
			list_options : ['item1','item2',...] only when format is ``list`` or ``multiple-list``,
			number_options: {
				from_number: 10, (for decimal is decimal) optional
				to_number: 20, (for decimal is decimal) optional
				currency: 'EUR' (optional),
				decimal_places: 1 (optional and required for decimal type only)
			},
			boolean_options: {
				default : 'true' | 'false' | null (when not selected null is assigned)
			}
			
		}
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>


## 32. Get Available Attribute Types List: POST /user/available/attribute/types

Used for Object Type page

Request

```
	{
		login_key: "logged in user token",
	}	
```

Response On Success

```
	{
		status: true,
		attribute_types: [
			{
				id: 1,
				name: 'Attribute Type 1'
			},
			...
		]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>



## 33. Get Available Object Types List: POST /user/available/object/types

This should return object types list.

Request

```
	{
		login_key: "logged in user token",
	}	
```

Response On Success

```
	{
		status: true <boolean>,
		object_types: [
			{
				id: 1,
				name: 'Object Type 1'
			},
			...
		]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

#Model Management

## 34. Get Model Manager Items: POST /models

Note that each folder can have multiple folders and multiple models. Each model can have multiple folder. Model can't contain other models but only folders.

Request

```
	{
		login_key: "logged in user token",
		options: { //optional
			id: 1,
			type: model or folder
		}
		page: 1
	}	
```

Response On Success

```
	{
		status: true,
		error: "",
		items: [
		   {
            id : 1,
            name : 'Root Folder 1',
            type : 'folder',
            created_on: "d/m/Y format date",
            created_by: "created by user name",
            updated_on: "d/m/Y format date",
            last_updated_by: "Another User",
            pages: 5,
            items: [] //add blank array
          },
         {
            id : 4,
            name : 'Root Model 1',
            type : 'model',
            created_on: "d/m/Y format date",
            created_by: "created by user name",
            updated_on: "d/m/Y format date",
            last_updated_by: "Another User",
            pages: 3,
            items : [] //add blank array
          },
		],
		page: 1,
		breadcrumbs: [
			{
				id: 1234,
				name: 'Folder 1'
			},
			...
		]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 35. Get Model Details (Model Viewer): POST /model/details

Note that each model can have multiple objects or no objects from the beginning.

Request

```
	{
		login_key: "logged in user token",
		ids: [1, 2] one or multiple model ids or folder|model,
		keyword: 'optional text',
		show: "details|items"
		page: will be sent from server for load data for that page
	}	
```

Response On Success

```
	{
		status: true,
		error: "",
		pages: 100,
		models: [
		   {
            id : 1,
            name : 'Model 1',
            type: 'model|folder|object|diagram',
            pages: 10
          },
         	{
            id : 11,
            name : 'Model 2',
            type: 'model|folder|object|diagram',
            pages : 1
          },
          ...
		]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 36. Create Object POST /create/object



Request

```
	{
		login_key: "logged in user token",
		object_data : {
			name : "Object 1",
			object_type_id: "1"
		},
		options: {
			id: 1, If set 0 then is root level,
			type: model or folder
		}
	}	
```

Response On Success

```
	{
		status: true,
		error: "",
		id: 1,
		name: "Object 1",
		pages: 10, page of parent object after adding new item, note that parent can be model and folder as well
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 37. Get Object(s) Details POST /object/details



Request

```
	{
		login_key: "logged in user token",
		object_ids: [1, 2], can be one or multiple inside array
	}	
```

Response On Success

```
	{
		status: true,
		error: "",
		settings: {
			date_format : "day month year",
			separator: "/"
		},
		object_type_attributes: [
			id : 1 //object N ID,
			name: "Object 1",
			object_type: "Object type 1",
			model_name : "Model 1 (note that model name be at any top level)",
			created_date: "Y-m-d H:i:s" (date or empty/null),
			created_by: "user name who created the record",
			updated_by: "user name who modified model/folder or it's attributes last time",
			updated_date: "Y-m-d H:i:s" (date or empty/null),
			description: "text",
			attributes: [
				{
                    attribute_id : '1',
                    name : 'Some Text',
                    type : 'text',
                    selected_value : 'Sample of text attribute'
                  },
                  {
                    attribute_id : '2',
                    name : 'Some Date',
                    type : 'date',
                    selected_value : '2021-04-16'
                  },
                  {
                    attribute_id : '3',
                    name : 'Some Decimal',
                    type : 'decimal',
                    selected_value : '4.0',
                    values : ['1.0','5.0'],
                    currency : 'USD',
                    decimal_spaces : "1"
                  },
                  {
                    attribute_id : '4',
                    name : 'Some Integer',
                    type : 'integer',
                    selected_value : '5',
                    values : ['1','10'],
                    currency : 'EUR'
                  },
                  {
                    attribute_id : '5',
                    name : 'Some Boolean',
                    type : 'boolean',
                    selected_value : 'true',
                  },
                  {
                    attribute_id : '6',
                    name : 'Some List',
                    type : 'list',
                    selected_value : 'Option 2',
                    values : ['Option 1', 'Option 2', 'Option 3', 'Option 4', 'Option 5'],
                  },
                  {
                    attribute_id : '7',
                    name : 'Some Multiple List',
                    type : 'multiple-list',
                    selected_value : ['Multiple 1', 'Multiple 2'],
                    values : ['Multiple 1', 'Multiple 2', 'Multiple 3', 'Multiple 4'],
                  },
			],
			{
				id : 2 //object N ID,
				name: "Object 1",
				object_type: "Object type 1",
				attributes: [
					...
				]
			}
		]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 38.1 Get Object(s) Relationships POST /object/relationship



Request

```
	{
		login_key: "logged in user token",
		object_id: 1,
		page: 1
	}	
```

Response On Success

```
	{
		status: true,
		error: "",
		pages: 1,
		relationships_data: [
          {
            relationship_id : '1',
            relationship_type_id : '1',
            name : 'Relationship Type From/To Combination Description 1',
            total_objects: "10"
          },
          {
            relationship_id : '2',
            relationship_type_id : '2',
            name : 'Relationship Type From/To Combination Description 2',
            total_objects: "0"
          },
          ...
      ]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 38.2 Get Object(s) Relationships Objects POST /object/relationship/objects



Request

```
	{
		login_key: "logged in user token",
		relationship_type_id: 1,
		relationship_id: 1,
		from_object_id: 1,
		page: 1
	}	
```

Response On Success

```
	{
		status: true,
		error: "",
		pages: 1,
		objects_data: [
          {
            id : '1',
            name : 'Object 1',
          },
          {
            id : '1,
            name : 'Object 2',
          },
          ...
      ]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 39. Update Object Attributes POST /update/object/attributes


Request

```
	{
		login_key: "logged in user token",
		object_attributes : {
			"object_id": "60c502c82918c870de37b69d",
	 	 	"attributes": [
			   {
			    "attribute_id": "60c502c82918c870de37b69d",
				value : 'text, date, list or any other value
			   },
			   
			   (for multiple-list the value can be splitted by __##__)
			   ...
			]
		},
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 40. Inline Editor Attribute Details POST /object/inline/details

Note: `model_column_width ` `object_name_column_width ` `width` should come from #57 for that logged in user. If width not found the `160` should be sent by default. If height of object not found the `30` should be sent by default.

Request

```
	{
		login_key: "logged in user token",
		object_ids: [1,2], (see notes)
		folder_id: 1, (see notes)
		model_id: 1, (see notes)
		page: 1
	},
	
	only 1 of these 3 can have value object_ids, folder_id, model_id.
	
```

Response On Success

```
	{
		status: true,
            error: '',
            pages: 4,
    		  settings: {
					date_format : "day month year",
					separator: "/"
			  },
            has_multiple_model_objects: true|false,
            model_column_width: "100",
            object_name_column_width: "100",
            settings: {
					date_format : "day month year",
					separator: "/"
				},
				selected_system_properties: {
					created_date: true|false,
					created_by: true|false,
					updated_date: true|false,
					updated_by: true|false,
					model_name: true|false,
					object_type: true|false,
					description: true|false,
				},
				system_properties_column_width: {
					created_date: 100,
					created_by: 100,
					updated_date: 100,
					updated_by: 100,
					model_name: 100,
					object_type: 100,
					description: 100
				},
            object_attributes: [
              {
                id : '1',
                parent_id : '1234',
                name : 'Object 1',
                object_type : 'Object Type 1',
                model_id: '1',
                object_type_id: '1',
                model_name: "Model 1",
                row_height: '30',
            		created_date: "Y-m-d H:i:s" (date or empty/null),
					created_by: "user name who created the record",
					updated_by: "user name who modified model/folder or it's attributes last time",
					updated_date: "Y-m-d H:i:s" (date or empty/null),
					description: "text",
                attributes : [
                  {
                    attribute_id: '1',
                    name: 'Some Text',
                    type: 'text',
                    selected_value: 'Sample of text attribute',
                  },
                  {
                    attribute_id: '2',
                    name: 'Some Date',
                    type: 'date',
                    selected_value: '2021-04-16',
                  },
                  {
                    attribute_id: '3',
                    name: 'Some Decimal',
                    type: 'decimal',
                    selected_value: '4.0',
                    values: ['1.0', '1.5'],
                    currency: 'USD',
                  },
                  {
                    attribute_id: '4',
                    name: 'Some Integer',
                    type: 'integer',
                    selected_value: '5',
                    values: ['1', '2'],
                    currency: 'EUR',
                  },
                  {
                    attribute_id: '5',
                    name: 'Some Boolean',
                    type: 'boolean',
                    selected_value: 'true',
                  },
                  {
                    attribute_id: '6',
                    name: 'Some List',
                    type: 'list',
                    selected_value: 'Option 2',
                    values: ['Option 1', 'Option 2', 'Option 3', 'Option 4', 'Option 5'],
                  },
                  {
                    attribute_id: '7',
                    name: 'Some Multiple List',
                    type: 'multiple-list',
                    selected_value: ['Multiple 1', 'Multiple 2'],
                    values: ['Multiple 1', 'Multiple 2', 'Multiple 3', 'Multiple 4'],
                  },
                  {
                    attribute_id: '8',
                    name: 'Another Text 8',
                    type: 'text',
                    selected_value: 'Cool text'
                  },
                  {
                    attribute_id: '9',
                    name: 'Event Date',
                    type: 'date',
                    selected_value: '2021-04-20'
                  },
                ]
              },
              {
                id: '2',
                name: 'Object 2',
                object_type: 'Object Type 2',
                attributes: [
 			 		...
 			 	  ],
              }
            ],
            selected_attributes : [
              {
                attribute_id : '1',
                name : 'Attribute 1',
                selected : true,
                width:50,
              },
              {
                attribute_id : '2',
                name : 'Attribute 2',
                selected : true,
                width:100,
              },
              {
                attribute_id : '9',
                name : 'Attribute 5',
                selected : true,
                width:200,
              },
              {
                attribute_id : '10',
                name : 'Attribute 6',
                selected : true,
                width:300,
              },
              ...
        	]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 41. Update Model Available Attributes (Model Viewer): POST /update/object/available/attributes

Notes: we will have one of object_ids|folder_id|model_id, similar to In Line Editor API. Received attributes (only in send list, we might have attributes on other pages but we work with request) should be marked as "selected" if found in model objects, folder objects or object(s). All their other attributes will get selected = false


Request

```
	{
		login_key: "logged in user token",
		object_ids: [1] (array or null containing 1 or multiple object ids)
		folder_id: 1, (null or folder id)
		model_id: 1, (null or model id),
		attributes: [
			{
				id: attribute_id,
				selected: true|false,
			},
			...
		]
	}	
```

Response On Success

```
	{
		status: true,
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 42. Create Folder (Model Manager and Viewer): POST /create/folder


Request

```
	{
		login_key: "logged in user token",
		name: 'Folder 1',
		options: {
			id : 1, if set 0 then is root level, in this case the type can be empty as well,
			type : model | folder
		}
	}	
```

Response On Success

```
	{
	    status: true,
	    error: '',
	    id: 10,
	    name : name,
	    pages: 3
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 43. Get Available Object Types List: POST /model/available/object-types


Request

```
	{
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
	    status: true,
	    error: '',
	    id: 10,
	    name : name,
	    pages: 3
	}
```

Response On Failure

```
	{
		status: true,
		error: '',
		object_types : [
			{
				id : 1,
				name : 'Object Type 1'
			},
			{
				id : 2,
				name : 'Object Type 2'
			},
			{
				id : 3,
				name : 'Object Type 3'
			},
			{
				id : 3,
				name : 'Object Type 4'
			}
		]
}	
```

<hr>

## 45. Delete Model/Folder items: POST /delete/models

Note that each deleted model can have multiple objects, folders and each deleted folder can have multiple folders, models, objects which means we should remove all in tree that associated with deleted item.

Request

```
	{
		login_key: "logged in user token",
		selected_items: [
			{
				id : 1,
				type : 'model'
			},
			{
				id : 2,
				type : 'folder'
			},
		],
		parent_page_ids: [0, 1]...
	}	
```

Response On Success

```
	{
		status: true,
		error: '',
		page_items: [
			{ id: 0, type : '', pages: 10 },
			{ id: 1, type : 'folder', pages: 4 },
			{ id: 4, type : 'model', pages: 3 },
		]
	}
	
	Here we send response of deleted item parents with pages
```

Response On Failure

```
	{
		status : false,
		error : "Something went wrong"
}	
```
<hr>

## 46. Model/Folder details: POST /model/folder/details



Request

```
	{
		login_key: "logged in user token",
		id: 1,
		type: model or folder
	}	
```

Response On Success

```
	{
		status: true,
		error: '',
		details: {
          id: 1,
          name: 'New Item',
          type: 'folder',
          created_on: '05/04/2021',
          updated_on: '05/04/2021',
          created_by: 'Robert Plant'
    	}
	}
```

Response On Failure

```
	{
		status : false,
		error : "Something went wrong"
	}	
```
<hr>

## 46. Update Folder or Model Name (Model Manager and Viewer): POST /update/model


Request

```
	{
		login_key: "logged in user token",
		name: 'Folder 1',
		id: '1', (this can be ID of model or folder)
	}	
```

Response On Success

```
	{
	    status: true,
	    error: '',
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 47. Update Object Name: POST /update/object


Request

```
	{
		login_key: "logged in user token",
		name: 'Object 1',
		id: '1', (this can be ID of object)
	}	
```

Response On Success

```
	{
	    status: true,
	    error: '',
	    model_id: '1234'
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 48. Delete Object: POST /delete/object


Request

```
	{
		login_key: "logged in user token",
		id: '1'
	}	
```

Response On Success

```
	{
	    status: true,
	    error: '',
		parent_id: "1234" (this can be folder or model id, the parent of the object),
		model_id: "12345"
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 49. Model and Folder Drag & Drop: POST /update/structure


Request

```
	{
		login_key: "logged in user token",
		id: 1 (model or folder id that we move to another place),
		new_parent_id: 2 (where to move the model/folder)
	}	
```

Response On Success

```
	{
	    status: true,
	    error: '',
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 50. Show Available and Selected Attributes POST /object/available/attributes

Here we display all (including selected and not selected) attributes found for objects with their selected/not selected status. Please note we need some pagination there as well.
<br><br>
Note: System properties should be in list too and they need to work with pagination like other attributes. We need to sort this attribtues result by ascending.


Request

```
	{
		login_key: "logged in user token",
		object_ids: [1,2], (see notes)
		folder_id: 1, (see notes)
		model_id: 1, (see notes),
		page: 1
	},
	
	only 1 of these 3 can have value object_ids, folder_id, model_id.
	
```

Response On Success

```
	{
		status: true,
		pages: 50,
		attributes: [
			{
				attribute_id: 1,
				name : 'Attribute 1',
				selected: true|false,
				type: 'attribute'
			},
			...
			{
				name : 'Created By',
				type: 'created_by'
			},
			{
				name : 'Created Date',
				type: 'created_date'
			},
			{
				name : 'Update By',
				type: 'updated_by'
			},
			{
				name : 'Object Type',
				type: 'object_type'
			},
			{
				name : 'Model',
				type: 'model'
			},
			{
				name : 'Description',
				type: 'description'
			},
			...
		]
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 51. Search Objects POST /objects/search


Request

```
	{
		login_key: "logged in user token",
		keyword: "text or empty string"
		page: 1
	},
	
```

Response On Success

```
	{
		status: true,
		pages: 100,
		objects: [
			{
				object_id: 1,
				name : 'Object 1',
			},
			{
				object_id: 2,
				name : 'Object 2',
			},
			...
		]
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 52. Search Relationship Types POST /relationship/types/search


Request

```
	{
		login_key: "logged in user token",
		keyword: "text or empty string",
		from_object_id: "1234",
		page: 1
	},
	
```

Response On Success

```
	{
		status: true,
		pages: 100,
		relationship_types: [
			{
				relationship_type_id: 1,
				name : 'Relationship Type 1',
			},
			{
				relationship_type_id: 2,
				name : 'Relationship Type 2',
			},
			...
		]
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 53. Create Relationship Type Combination POST /relationship/types/create/combination

Note: description can be null or string

Request

```
	{
		login_key: "logged in user token",
		relationship_type_id: "1"
		from_object_id: "2",
		to_object_id: "3",
		description: "some description here" (Optional, can be null as well)
	},
	
```

Response On Success

```
	{
		status: true,
		pages: 100,
		relationship_types: [
			{
				relationship_type_id: 1,
				name : 'Relationship Type 1',
			},
			{
				relationship_type_id: 2,
				name : 'Relationship Type 2',
			},
			...
		]
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 54. Add Relationship POST /relationship/create

Note: description can be null or string

Request

```
	{
		login_key: "logged in user token",
		relationship_type_id: "1"
		from_object_id: "2",
		to_object_id: "3",
	},
	
```

Response On Success

```
	{
		status: true
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 55. Object To (Relationship) search POST /relationship/to/object/search

Notes: Each Relationship Type can have multiple combinations of the same "from" object, here we return "To" objects that found in combinations matching "From" object and searching inside "Relationship Type".


Request

```
	{
		login_key: "logged in user token",
		from_object_id: "1234",
		relationship_type_id: "1234",
		keyword: "text or empty string"
		page: 1
	},
	
```

Response On Success

```
	{
		status: true,
		pages: 100,
		objects: [
			{
				object_id: 1,
				name : 'Object 1',
			},
			{
				object_id: 2,
				name : 'Object 2',
			},
			...
		]
	}
```	

## 56. Search Objects POST /object/type/search


Request

```
	{
		login_key: "logged in user token",
		keyword: "text or empty string"
		page: 1
	},
	
```

Response On Success

```
	{
		status: true,
		pages: 100,
		object_types: [
			{
				object_type_id: 1,
				name : 'Object 1',
			},
			{
				object_type_id: 2,
				name : 'Object 2',
			},
			...
		]
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```


## 57. Resize In Line Editor Column Width POST /resize/inline/editor/column

Notes: In case we resize attribute you will receive "attribute_id" and type `attribute`. In case we resize model or object_name the "attribute_id" would be `null` and type accordingly `model` or `object_name`. Need to be stored unique by each logged in user. userInlineEditorColumns collection can handle it with `user_id`, `attribute_id`, `type` and `width` columns.

Request

```
	{
		login_key: "logged in user token",
		attribute_id: "1", (optional, can be null as well),
		type : "model|object_name|attribute|created_by|created_date|updated_by|updated_date|object_type|description"
		width: "10" (number)
	},
	
```

Response On Success

```
	{
		status: true
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 58. Resize In Line Editor Row Height POST /resize/inline/editor/cell-height

Request

```
	{
		login_key: "logged in user token",
		object_id: "1",
		height: "10" (number)
	},
	
```

Response On Success

```
	{
		status: true
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 59. Model/Folder Details POST /model/folder/info

Notes: for the `updated_by` and `updated_date` we need to store user who last time modified the record. In case of model it can be any change made inside that model, in case of folder only when we change something inside that folder. In case of diagram it will be handled by note change or other API things, specs will be given<br>
For the `description` it's a new field and should contain some text for notes for each folder/model. Can be empty by default until we add it.


Request

```
	{
		login_key: "logged in user token",
		id: "1",
		type: "model|folder|diagram"
	},
	
```

Response On Success

```
	{
		status: true,
		info: {
			created_date: "Y-m-d H:i:s" (date or empty/null),
			created_by: "user name who created the record",
			updated_by: "user name who modified model/folder or it's attributes last time",
			updated_date: "Y-m-d H:i:s" (date or empty/null),
			description: "text"
		}
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 60. Model/Folder Update Description POST /model/folder/update/info


Request

```
	{
		login_key: "logged in user token",
		id: "1",
		type: "model|folder|diagram",
		description: "empty or new description"
	},
	
```

Response On Success

```
	{
		status: true
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```


## 61. Object Update Description POST /object/update/description


Request

```
	{
		login_key: "logged in user token",
		id: "1",
		description: "empty or new description"
	},
	
```

Response On Success

```
	{
		status: true
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 62. Add Date Format to Settings POST /settings/add/date/format


Request

```
	{
		login_key: "logged in user token",
		format: "day month year", (or this can be month day year, any combination, save as string the way received from the from the request)
		separator: "/" (this can be " " space too),
		diagram_new_tab: true|false,
	},
	
```

Response On Success

```
	{
		status: true,
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```


## 63. Get Date Format Settings POST /settings/date/format


Request

```
	{
		login_key: "logged in user token",
	},
	
```

Response On Success

```
	{
		status: true,
		format: "day month year", (or this can be month day year, any combination)
		separator: "/" (this can be " " space too),
		diagram_new_tab: true|false (boolean)
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 64. Model Viewer Folder/Object Drag & Drop: POST /update/model/viewer/structure

Notes: This drag & drop is for Model Viewer left sidebar (tree) view. We can drag folders or objects to another folders or move them to top under model root level.


Request

```
	{
		login_key: "logged in user token",
		id: 1 (object|folder id that we move to another place),
		new_parent_id: 2 (where to move the object/folder)
	}	
```

Response On Success

```
	{
	    status: true,
	    error: '',
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 65. Add Multiple Inline Editor Objects: POST /add/multiple/objects

Notes: The attributes can be empty array as well, it's the same structure we have for update attributes page. The same values.  For multiple values it's splitted using `__##__` as in #39.


Request

```
	{
		login_key: "logged in user token",
		new_objects: [
			{
				name: "Object Name",
				model_id: "1",
				object_type_id: "1",
				attributes: [
					{
						"attribute_id" : "1",
						"value" : "some value here"
					},
					...
				]
			},
			...
		]
		parent_id: 1,
		parent_type: (model|folder)
	}	
```

Response On Success

```
	{
	    status: true,
	    object_ids: [1,2],
	    pages: 10, (pages after adding new items for selected parent)
	    error: '',
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>
## 66. Update Object System Properties POST /update/object/available/system/properties


Notes: we will have one of object_ids|folder_id|model_id, similar to In Line Editor API. Received params (only in send list, we might have properties on other pages but we work with request) should be marked as "selected" if found in model objects, folder objects or object(s).

Request

```
	{
		login_key: "logged in user token",
		object_ids: [1] (array or null containing 1 or multiple object ids)
		folder_id: 1, (null or folder id)
		model_id: 1, (null or model id),
		properties: [
			{
				type: created_by|created_date|updated_by|updated_date|object_type|model_name|description,
				selected: true|false,
			},
			...
		]
	}	
```

Response On Success

```
	{
		status: true,
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 67. Update Objects Type POST /update/model/object/type


Request

```
	{
		login_key: "logged in user token",
		object_id: 1
		object_type_id: 1
```

Response On Success

```
	{
		status: true,
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 68. List Of Diagram Types: POST /diagram/types

Request

```
	{
		login_key: "logged in user token",
		sort: "name_asc|name_desc|created_date_asc|created_date_desc|updated_by_asc|updated_by_desc|updated_date_asc|updated_date_desc",
		page: 1 (this could be used for the pagination)
	}	
```

Response On Success

```
	{
		status: true <boolean>,
		diagram_types: [
			{
				id: 1,
				name: "Object Type 1",
				created_date: "Y-m-d H:i:s" (date or empty/null),
			created_by: "user name who created the record",
			updated_by: "user name who modified model/folder or it's attributes last time",
			updated_date: "Y-m-d H:i:s" (date or empty/null),
			},
			...
		],
		pages: 20 total pages found
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>


## 69. Add Diagram Type: POST /diagram/types/create

Request

```
	{
		name: "Diagram Type 1",
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 70. Edit Diagram Type: POST /update/diagram/type

Request

```
	{
		id: 1,
		login_key: "logged in user token",
		name: "Diagram Type 1",
	}	
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 71. Delete Diagram Type: POST /delete/diagram/type

Request

```
	{
		diagram_type_ids: [1, 2] (one or multiple diagram type ids),
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true,
		pages: 1, (pages remaining after deleted items),
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 72. Diagram Type Details: POST /diagram/type/detail

Request

```
	{
		id: 1,
		login_key: "logged in user token"
	}	
```

Response On Success

```
	{
		status: true <boolean>,
		diagram_type_data: {
			id: 1,
			name: "Diagram type 1",
		}
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 73. Search Diagram Types POST /diagram/type/search

Notes: Please sort this by match ascending (not case sensitive). Bulk `page` argument by calculating 20 items per page

Request

```
	{
		login_key: "logged in user token",
		keyword: "text or empty string"
		page: 1
	},
	
```

Response On Success

```
	{
		status: true,
		pages: 100,
		diagram_types: [
			{
				diagram_type_id: 1,
				name : 'Diagram Type 1',
			},
			{
				diagram_type_id: 2,
				name : 'Diagram Type 2',
			},
			...
		]
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

## 74. Create Diagram POST /create/diagram


Request

```
	{
		login_key: "logged in user token",
		diagram_data : {
			name : "Object 1",
			diagram_type_id: "1"
		},
		options: {
			id: 12345678, If set 0 then is root level,
			type: model or folder
		}
	}	
```

Response On Success

```
	{
		status: true,
		error: "",
		id: 1,
		name: "Diagram 1",
		pages: 10, page of parent childs after adding new item, note that parent can be model and folder as well
	}	
```
<hr>

## 75. Update Diagram: POST /update/diagram


Request

```
	{
		login_key: "logged in user token",
		name: 'Diagram 1',
		id: '1'
	}	
```

Response On Success

```
	{
	    status: true,
	    error: '',
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 76. Delete Object: POST /delete/diagram


Request

```
	{
		login_key: "logged in user token",
		id: '1'
	}	
```

Response On Success

```
	{
	    status: true,
	    error: '',
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```
<hr>

## 77. Get Object Property Details POST /object/property/details



Request

```
	{
		login_key: "logged in user token",
		object_id: 1234
	}	
```

Response On Success

```
	{
		status: true,
		error: "",
		id : 1 //object N ID,
		name: "Object 1",
		object_type: "Object type 1",
		model_name : "Model 1 (note that model name be at any top level)",
		created_date: "Y-m-d H:i:s" (date or empty/null),
		created_by: "user name who created the record",
		updated_by: "user name who modified model/folder or it's attributes last time",
		updated_date: "Y-m-d H:i:s" (date or empty/null),
		description: "text",
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 78. Update Diagram XML POST /diagram/update/xml

Notes: `diagram_libraries` can be empty too, which means we should delete added Diagram custom libraries. The `id` inside `custom_libraries` if null/empty means we should add new library, for the xml it can be empty too but in that case we don't have to update xml, only when it has a value, the name should be updated too. The `svg` can be empty or svg string.<br>

SVG padding: `svg_padding_top` and `svg_padding_left` can be null too and number too (even 0). If null, we don't have to update those values, only when is a number.



Request

```
	{
		login_key: "logged in user token",
		diagram_id: 1234,
		xml: (this is for Diagram's XML content, each Diagram can have only 1 xml which is the same what we draw in Diagram Editor),
		diagram_libraries: [
			{
				id: "12345", (in case of new items it's empty),
				name: "Custom Library 1",
				xml: "we need to check if xml has a value, if presents then only we updating XML, otherwise ignoring and updating only name"
			}
		],
		svg: "<svg...",
		svg_padding_top: 10 "please note that this can be null too",
		svg_padding_left: 10 "please note that this can be null too",
	}
```

Response On Success

```
	{
		status: true,
		error: "",
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 79. Get Diagram XML POST /diagram/get/xml

Notes: we might/might not have libraries, but need always include argument in response, if empty just `libraries:[]`. Sort by default library name ascending, then manual library created date asc.

Request

```
	{
		login_key: "logged in user token",
		diagram_id: 1234,
	}
```

Response On Success

```
	{
		status: true,
		error: "",
		xml: "xml content here or empty string if no xml is created yet",
		name: "Diagram name here",
		model_name: "Model name here",
		created_date: "Y-m-d H:i:s" (date or empty/null),
		created_by: "user name who created the record",
		updated_by: "user name who modified model/folder or it's attributes last time",
		updated_date: "Y-m-d H:i:s" (date or empty/null),
		diagram_parent_id: "diagram parent's id",
		"libraries": [
			{
				id: "1234",
				name: "General",
				code: "general",
				is_default: "1|0",
				xml: "We don't need an xml for default libraries, only for custom ones"
			},
			...
		],
		"diagram_libraries": [
			{
				id: "1234",
				name: "General",
				xml: "We don't need an xml for default libraries, only for custom ones"
			},
			...
		]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 80. Get Diagram Default Selected Libraries POST /diagram/default/libraries

Request

```
	{
		login_key: "logged in user token",
		diagram_id: "1234"
	}
```

Response On Success

```
	{
		status: true,
		error: "",
		libraries: [
			{
				id: "1234",
				name: "General",
				code: "general"
			},
			{
				id: "1234",
				name: "Misc",
				code: "misc"
			},
			{
				id: "1234",
				name: "Bootstrap Components",
				code: "bootstrap"
			},
			...
		],
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 81. Get Diagram Custom Libraries POST /diagram/custom/libraries

Request

```
	{
		login_key: "logged in user token",
		diagram_id: "1234"
	}
```

Response On Success

```
	{
		status: true,
		error: "",
		libraries: [
			{
				id: "1234",
				name: "Lib1",
				code: "some_lib",
				encoded_xml: "encoded xml file here"
			},
			{
				id: "1234",
				name: "Lib2",
				code: "some_lib2",
				encoded_xml: "encoded xml file here"
			},
			...
		],
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 82. Delete Diagram Custom Library POST /diagram/delete/custom/library

Request

```
	{
		login_key: "logged in user token",
		diagram_id: "1234",
		custom_library_id: "12345"
	}
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 83. Add Diagram Custom Library POST /diagram/add/custom/library

Request

```
	{
		login_key: "logged in user token",
		diagram_id: "1234",
		name: "Lib1",
		code: "empty or some string, can be json too",
		encoded_xml: "string, no limits"
	}
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 84. Diagram Type Library Items POST /diagram/type/library/items

Notes: is_default stays if the library is default or manually added for this Diagram Type. It can be either 1 or 0.

Request

```
	{
		login_key: "logged in user token",
		diagram_type_id: "1234"
	}
```

Response On Success

```
	{
		status: true,
		libraries: [
			{
				id: '1',
          	name: 'General',
          	is_default: '1', (either 1 or 0)
          	shape_types: [
		          {
		            id: '100',
		            name: 'Rectangle',
		            svg: '<svg...',
		          },
		          {
		            id: '101',
		            name: 'Square',
		            svg: '<svg...',
		          },
		          ...
      			]
			},
			{
				id: '2',
          	name: 'Advanced',
          	is_default: '1', (either 1 or 0)
          	shape_types: [
		          {
		            id: '102',
		            name: 'Flowbox',
		            svg: '<svg...',
		          },
		          {
		            id: '103',
		            name: 'TreeView',
		            svg: '<svg...',
		          },
		          ...
      			]
			},
			...
   		],
   		combinations: [
   			{
   				id: 1,
   				library_id: '1',
   				library_name: "Lib1",
   				shape_type_id: '123',
      			shape_type_name: 'Rectangle',
      			shape_type_svg: '<svg...',
      			object_type_id: '2',
      			object_type_name: 'Object Type 1'
   			},
   			{
   				id: 2,
   				library_id: '2',
   				library_name: "Lib2",
   				shape_type_id: '123',
      			shape_type_name: 'TreeView',
      			shape_type_svg: '<svg...',
      			object_type_id: '2',
      			object_type_name: 'Object Type 2'
   			},
   			...
   		],
		connector_combinations: [
   			{
   				id: 1,
   				library_id: '1',
   				library_name: "Lib1",
   				shape_type_id: '123',
      			shape_type_name: 'Rectangle',
      			shape_type_svg: '<svg...',
      			relationship_type_id: '2',
      			relationship_type_name: 'Object Type 1'
   			},
   			...
		],
	}
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 85. Object Types List POST /object/types/list

Notes: by default should be set getting 50 items per request. Sort by ascending.

Request

```
	{
		login_key: "logged in user token",
		page: 1
	}
```

Response On Success

```
	{
		status: true,
		pages: 2 (this indicates how many pages we have for 50 items calc, can be 0 too),
		object_types: [
			{
				id: 1,
				name: 'Object Type 1',
			},
			{
				id: 2,
				name: 'Object Type 2',
			},
		],
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 86. Update Diagram Type Combinations POST /diagram/type/update/combinations

Notes: 

1. The `library_items` is marking the libraries user mentioned to see on their dashboard for that DiagramType. The `is_default` flag is only for backend to easily get the right collection, no need to use that to update the is_default flag. If user has 10 default libraries and received only 2 from this API then the rest needs to be removed from this view. If user deleted not default libraries (the manual ones uplaoded for this DiagramType) then we checking if they are not in list and then deleting from database too. Need to add this `library_items` logic first, then `combinations` as we might have new library added and associated Shape Types.

2. The `combinations` can be an empty array as well when no combinations selected. There is validation in front end that is not allowing to save the same object_type_id or shape_type_id multiple times. In backend the same validation should appear (for now only on DiagramType level). So if shape_type_id/object_type_id repeated twice we should not insert new data (this is in case they have the same page open in multiple browser tabs and modified in both cases).

Request

```
	{
		login_key: "logged in user token",
		diagram_type_id: 1,
		library_items: [
			{
				id: 1,
				is_default: 1 (is either 1 or 0)
			}
		],
		combinations: [
			{
				id: 1, (this will not present for a new items),
				library_id: 1,
				shape_type_id: 1,
				object_type_id: 1,
				library_id: 1
			}
		],
		connector_combinations: [
			{
				id: 1, (this will not present for a new items),
				library_id: 1,
				shape_type_id: 1,
				relationship_type_id: 1,
			}
		],
	}
```

Response On Success

```
	{
		status: true
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 87. Add Diagram Type Library POST /diagram/type/add/library


Request

```
	{
		login_key: "logged in user token",
		diagram_type_id: "1234",
		name: "Lib1",
		code: "empty or some string, can be json too",
		encoded_xml: "string, no limits",
		shapes: [
			{
				svg: '<svg...',
				encoded_xml: 'encoded xml string'
				type: 'shape|image'
			},
			...
		]
	}
```

Response On Success

```
	{
		status: true,
		id: "1234" (library id),
		shapes: [
			{
				id: "12345",
				svg: '<svg...',
				type: 'shape|image'
			},
			...
		]
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 88. Get Diagram Object Shape XML POST /diagram/object/shape

Notes: Eeach Object has Object Type (one to one). Object Types have shape combinations for each Diagram Type. <br> Based on Diagram we are getting it's Diagram Type then checking if we have shape assigned to that Object Type for this specific Diagram Type. If so, sending shape's encoded_xml.

Object -> (one to one) -> Object Type<br>
Diagram -> (one to one) -> Diagram Type -> (combinations) -> {Object Type match with shape}

Request

```
	{
		login_key: "logged in user token",
		diagram_id: "1234",
		object_id: "1234",
	}
```

Response On Success

```
	{
		status: true,
		name: "object name", (we should always have this name as getting object_id)
		library_id: "1234",
		shape_type_id: "1234",
		shape_type_name: "some name if presents or null",
		encoded_xml: "Either shape encoded xml or just null",
		svg: "<svg..."
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 89. Relationship Types List POST /relationship/types/list

Notes: by default should be set getting 50 items per request. Sort by ascending.

Request

```
	{
		login_key: "logged in user token",
		page: 1
	}
```

Response On Success

```
	{
		status: true,
		pages: 2 (this indicates how many pages we have for 50 items calc, can be 0 too),
		relationship_types: [
			{
				id: 1,
				name: 'Relationship Type 1',
			},
			{
				id: 2,
				name: 'Relationship Type 2',
			},
		],
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 90. Detect Shape ID From XML POST /get/shape/from/xml

Notes: the xml string would be received along with the Diagram ID, we need to get the Diagram Type from that Diagram and check if that Diagram Type has any shape with that XML string matching. Please check only for Diagram Type level shapes (default + manual) not Diagram manual lib added ones.

Request

```
	{
		login_key: "logged in user token",
		diagram_id: 1234,
		encoded_xml: "some encoded string here"
	}
```

Response On Success

```
	{
		status: true,
		shape_id: '1234',
		library_id: '12345',
		is_default: '1 or 0',
		relationship_type_id: "12345",
		object_type_id: "12345"
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 91. Diagram New Object & Relationship Processing POST /diagram/handle/objects/relationships


Request

```
{
	items: [
		{
			mx_temp_id: '1234',
			object_id: '1234 or null',
			shape_xml: '<...',
			name: '<...'
		},
		{
			mx_temp_id: '1234',
			shape_xml: '<...',
			name: '<...'
		},
	],
	relationships: [
		{
			mx_relationship_temp_id: '1234',
			relationship_shape_xml: '<...',
			mx_temp_from_id: '1234',
			mx_temp_to_id: '1234'
		},
		...
	]
}
```

Response On Success

```
	{
		status: true,
		relationships: [
			{
				mx_temp_id: '1234', (This is mx_relationship_temp_id)
				relationship_type_id: "12345",
				relationship_id: "1234" (new created relationship id)
			},
			...
		],
		objects: [
			{
				mx_temp_id: '1234',
				object_id: '12345',
				name: "Object Name"
			},
			...
		]
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 92. Get Diagram SVG Details POST /get/diagram/svg


Request

```
{
		login_key: "logged in user token",
		diagram_id: 1234
}
```

Response On Success

```
	{
		status: true,
		svg: "<svg...",
		svg_padding_top: 10,
		svg_padding_left: 20
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 93. Get Object/Relationship/Shape Details POST /diagram/object/relationship/details

Notes: `object_id`, `relationship_type_id`,`relationship_id` can be null too or any of them. First we need to check if we have `object_id` received, then we checking if we have that object, if so, returning the same response we have in #37 API, please copy logic from there (if object found, sending "Response 1", see bellow). If we have `object_id` but no match in database, then we are checking the `cell_xml` to see if it's matching with any Shape/Object Type if so, we are sending "Response 2".

If we receive `relationshi_id` then we checking if we have a match for that `relationship_id` and showing "Response 2".

If we receive `relationship_type_id` then we checking if we have a match for that `relationship_type_id` and showing "Response 2".


If we have no `object_id` and no `relationship_type_id` or we have ids but no match in db, then we trying to get it by `cell_xml` if we have it. Then showing "Response 2".

In case of no match by `cell_xml` too, then showing "Response 2" with `type` = `not_found`




Request

```
	{
		login_key: "logged in user token",
		object_id: "1234",
		relationship_id: "1234",
		relationship_type_id: "1234",
		cell_xml: "some xml here or empty string"
	}	
```

`Response 1` when object found 

```
	{
		status: true,
		error: "",
		type: "object"
		settings: {
			date_format : "day month year",
			separator: "/"
		},
		object_type_attributes: [
			id : 1 //object N ID,
			name: "Object 1",
			object_type: "Object type 1",
			model_name : "Model 1 (note that model name be at any top level)",
			created_date: "Y-m-d H:i:s" (date or empty/null),
			created_by: "user name who created the record",
			updated_by: "user name who modified model/folder or it's attributes last time",
			updated_date: "Y-m-d H:i:s" (date or empty/null),
			description: "text",
			attributes: [
				{
                    attribute_id : '1',
                    name : 'Some Text',
                    type : 'text',
                    selected_value : 'Sample of text attribute'
                  },
                  {
                    attribute_id : '2',
                    name : 'Some Date',
                    type : 'date',
                    selected_value : '2021-04-16'
                  },
                  {
                    attribute_id : '3',
                    name : 'Some Decimal',
                    type : 'decimal',
                    selected_value : '4.0',
                    values : ['1.0','5.0'],
                    currency : 'USD',
                    decimal_spaces : "1"
                  },
                  {
                    attribute_id : '4',
                    name : 'Some Integer',
                    type : 'integer',
                    selected_value : '5',
                    values : ['1','10'],
                    currency : 'EUR'
                  },
                  {
                    attribute_id : '5',
                    name : 'Some Boolean',
                    type : 'boolean',
                    selected_value : 'true',
                  },
                  {
                    attribute_id : '6',
                    name : 'Some List',
                    type : 'list',
                    selected_value : 'Option 2',
                    values : ['Option 1', 'Option 2', 'Option 3', 'Option 4', 'Option 5'],
                  },
                  {
                    attribute_id : '7',
                    name : 'Some Multiple List',
                    type : 'multiple-list',
                    selected_value : ['Multiple 1', 'Multiple 2'],
                    values : ['Multiple 1', 'Multiple 2', 'Multiple 3', 'Multiple 4'],
                  },
			],
			{
				id : 2 //object N ID,
				name: "Object 1",
				object_type: "Object type 1",
				attributes: [
					...
				]
			}
		]
	}	
```

`Response 2` when object cell match found or relationship cell match found, we can have either object_id with it's data or relationship_type_id with it's data

```
	{
		status: true,
		error: "",
		type: "object_type|relationship_type|relationship|not_found",
		object_type_name: "Object Type Name",
		object_type_id: "1",
		relationship_type_name: "Some relationship type name",
		relationship_type_id: "1",
		shape_name: "Shape Name",
		library_name: "Library Name",
		from_object_name: "Object 1",
		to_object_name: "Object 2"
	}
```	

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 94. Delete Relationships POST /delete/relationships


Request

```
	{
		relationship_ids: [
			'123',
			'1234',
			...
		]
	}
```

Response On Success

```
	{
		status: true,
	}	
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 95. Get Relationship Details POST /get/relationship/details


Request

```
	{
		relationship_id: "1234"
	}
```

Response On Success

```
	{
		status: true,
		relationship_type_name: "Type1",
		from_object_name: "Object1",
		to_object_name: "Object2",
		created_date: "Y-m-d H:i:s",
		created_by: "User1"
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>


## 96. Get Relationship Type Details POST /get/relationship/type/details


Request

```
	{
		relationship_type_id: "1234"
	}
```

Response On Success

```
	{
		status: true,
		relationship_type_name: "Type1",
		created_date: "Y-m-d H:i:s",
		created_by: "User1",
		updated_date: "Y-m-d H:i:s",
		updated_by: "User1",
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>

## 97. Get Diagram Settings POST /get/diagram/settings


Response On Success

```
	{
		diagram_new_tab: true|false
	}
```

Response On Failure

```
	{
		status: false,
		error: "error string from the server"
	}	
```

<hr>
