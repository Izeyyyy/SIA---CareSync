import { useEffect, useState } from "react";
import { getUsers } from "./services/userManagementService";
import UserTable from "./components/UserTable";
import EditUserModal from "./components/EditUserModal";


export default function UserManagement(){

    const [users, setUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);


    useEffect(() => {

        loadUsers();

    }, []);


    const loadUsers = async () => {

        try {

            const response = await getUsers();

            setUsers(response.data);

        } catch(error){

            console.error(
                "Failed loading users:",
                error
            );

        }

    };


    return (

        <div>

            <h2>
                User Management
            </h2>


            <UserTable 
                users={users}
                onEdit={setSelectedUser}
            />


            <EditUserModal
                key={selectedUser?.id}
                user={selectedUser}
                onClose={() => setSelectedUser(null)}
                onSave={(updatedUser)=>{

                    console.log(updatedUser);

                    setSelectedUser(null);

                }}
            />


        </div>

);

}