import { useAuth } from "../../context/AuthContext";
import { Bell } from "lucide-react";

export default function TopNavbar() {

    const { user } = useAuth();

    return (

        <header className="dashboard-navbar">

            <div className="navbar-left">

                <h2>

                    Welcome back,

                    {" "}

                    {user?.firstName || "User"}

                </h2>

            </div>

            <div className="navbar-right">

                <button className="notification-btn">

                    <Bell size={20}/>

                </button>

                <div className="navbar-profile">

                    <div>

                        <div className="profile-name">

                            {user?.firstName} {user?.lastName}

                        </div>

                        <div className="profile-role">

                            {user?.role}

                        </div>

                    </div>

                </div>

            </div>

        </header>

    );

}