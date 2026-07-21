import UserStatusBadge from "./UserStatusBadge";

export default function UserTable({ users, onEdit, onStatusChange, onResetPassword }) {


    return (

        <div className="dashboard-card">

            <table className="user-table">

                <thead>

                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>

                </thead>


                <tbody>


                {users.map((user)=>(

                    <tr key={user.id}>

                        <td>
                            {user.firstName} {user.lastName}
                        </td>


                        <td>
                            {user.email}
                        </td>


                        <td>
                            {user.role}
                        </td>


                        <td>
                            <UserStatusBadge active={user.active}/>
                        </td>


                        <td>

                            <button onClick={() => {
                                onEdit(user);
                            }}>
                                Edit
                            </button>


                            <button onClick={() => {
                                onStatusChange(user.id, !user.active);
                            }}>
                                {
                                    user.active
                                    ? "Deactivate"
                                    : "Activate"
                                }
                            </button>
                            <button
                                onClick={() => onResetPassword(user)}
                            >
                                Reset Password
                            </button>

                        </td>


                    </tr>

                ))}


                </tbody>

            </table>


        </div>

    );

}