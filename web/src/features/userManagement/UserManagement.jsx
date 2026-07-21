import { useEffect, useState } from "react";
import { getUsers, updateUser, updateUserStatus, resetPassword } from "./services/userManagementService";
import UserTable from "./components/UserTable";
import EditUserModal from "./components/EditUserModal";
import ResetPasswordModal from "../../components/dashboard/ResetPasswordModal";
import { useToast } from "../../context/ToastContext";


export default function UserManagement({ onUserUpdated }){

    const [users, setUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);
    const [search, setSearch] = useState("");
    const [resetUser, setResetUser] = useState(null);
    const { showToast } = useToast();


    useEffect(() => {

        loadUsers(search);

    }, [search]);


    const loadUsers = async (searchText = "") => {

        try {

            const response = await getUsers(searchText);

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

            <input
                type="text"
                placeholder="Search by name or email..."
                value={search}
                onChange={(e) => setSearch(e.target.value)}
                style={{
                    width: "300px",
                    padding: "8px",
                    marginBottom: "16px"
                }}
            />


           <UserTable
    users={users}
    onEdit={setSelectedUser}

    onStatusChange={async (id, active) => {

        try {

            await updateUserStatus(id, active);

            await loadUsers();

            if (onUserUpdated) {
                onUserUpdated();
            }

        } catch (error) {

            console.error(
                "Failed updating status:",
                error
            );

        }

    }}

    onResetPassword={(user) => {
        setResetUser(user);
    }}
    />
            <ResetPasswordModal
                user={resetUser}
                onClose={() => setResetUser(null)}
                onConfirm={async () => {

                    try {

                        await resetPassword(resetUser.id);

                        setResetUser(null);

                        await loadUsers();

                        if (onUserUpdated) {
                            onUserUpdated();
                        }

                        showToast(
                            "Password reset successfully. Default password is now 123456."
                        );

                    } catch (error) {

                        console.error(
                            "Failed resetting password:",
                            error
                        );

                        showToast(
                            "Failed resetting password.",
                            "error"
                        );

                    }

                }}
            />


            <EditUserModal
                key={selectedUser?.id}
                user={selectedUser}
                onClose={() => setSelectedUser(null)}
                onSave={async (updatedUser)=>{

                    try {

                        await updateUser(
                            updatedUser.id,
                            updatedUser
                        );


                        await loadUsers();

                        if (onUserUpdated) {
                            onUserUpdated();
                        }


                        setSelectedUser(null);


                    } catch(error){

                        console.error(
                            "Failed updating user:",
                            error
                        );

                    }

                }}
            />


        </div>

);

}