export default function DoctorDashboard() {

    const user = JSON.parse(localStorage.getItem("user"));

    return (
        <div>
            <h1>Doctor Dashboard</h1>
            <p>Welcome {user?.firstName}</p>
            <p>Role: {user?.role}</p>
        </div>
    );
}