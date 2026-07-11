import { useEffect, useState } from "react";


export default function EditUserModal({ user, onClose, onSave }) {

    const [form, setForm] = useState({
        firstName: "",
        lastName: "",
        email: "",
        role: ""
    });

    console.log("Current form:", form);


    useEffect(() => {


        if(user){

            setForm({
                firstName: user.firstName,
                lastName: user.lastName,
                email: user.email,
                role: user.role
                ? user.role.charAt(0).toUpperCase() + user.role.slice(1)
                : ""
            });

        }

    }, [user]);



    const handleChange = (e)=>{

        setForm({
            ...form,
            [e.target.name]: e.target.value
        });

    };



    const handleSubmit = (e)=>{

        e.preventDefault();

        onSave({
            ...user,
            ...form
        });

    };



    if(!user){
        return null;
    }



    return (

        <div className="modal-overlay">

            <div className="modal-card">


                <h2>
                    Edit User
                </h2>


                <form onSubmit={handleSubmit}>

                    First Name:
                    <input
                        name="firstName"
                        value={form.firstName}
                        onChange={handleChange}
                    />

                    Last Name:
                    <input
                        name="lastName"
                        value={form.lastName}
                        onChange={handleChange}
                    />

                    Email:
                    <input
                        name="email"
                        value={form.email}
                        onChange={handleChange}
                    />


                    Role:
                    <select
                        name="role"
                        value={form.role}
                        onChange={handleChange}
                    >

                        <option value="Admin">
                            Admin
                        </option>

                        <option value="Doctor">
                            Doctor
                        </option>

                        <option value="Clinic Staff">
                            Clinic Staff
                        </option>


                    </select>



                    <div className="modal-actions">

                        <button type="submit">
                            Save
                        </button>


                        <button
                            type="button"
                            onClick={onClose}
                        >
                            Cancel
                        </button>

                    </div>


                </form>


            </div>

        </div>

    );

}