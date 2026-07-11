import UserStatusBadge from "./UserStatusBadge";

export default function UserTable({ users, onEdit }) {


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
    console.log("Clicked:", user);
    onEdit(user);
}}>
                                Edit
                            </button>


                            <button>
                                {
                                    user.active
                                    ? "Deactivate"
                                    : "Activate"
                                }
                            </button>

                        </td>


                    </tr>

                ))}


                </tbody>

            </table>


        </div>

    );

}