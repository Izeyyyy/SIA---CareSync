export default function TopNavbar() {

    const user = JSON.parse(
        localStorage.getItem("user")
    );


    return (
        <header className="dashboard-navbar">

            <h3>
                Admin Workspace
            </h3>


            <span>
                {user?.firstName || "Administrator"}
            </span>

        </header>
    );
}