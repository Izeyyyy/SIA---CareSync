import "../../styles/dashboard.css";

export default function DashboardLayout({ children }) {
    return (
        <div className="dashboard-layout">

            <aside className="dashboard-sidebar">
                Sidebar
            </aside>

            <div className="dashboard-main">

                <header className="dashboard-navbar">
                    Navbar
                </header>

                <main className="dashboard-content">
                    {children}
                </main>

            </div>

        </div>
    );
}