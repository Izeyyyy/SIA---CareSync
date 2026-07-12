import "../../styles/dashboard.css";

import Sidebar from "./Sidebar";
import TopNavbar from "./TopNavbar";

export default function DashboardLayout({ children }) {

    return (

        <div className="dashboard-layout">

            <Sidebar />

            <div className="dashboard-main">

                <TopNavbar />

                <main className="dashboard-content">

                    {children}

                </main>

            </div>

        </div>

    );

}