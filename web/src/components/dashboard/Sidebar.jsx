import { useState } from "react";
import { ChevronLeft, ChevronRight } from "lucide-react";
import { useAuth } from "../../context/AuthContext";
import SidebarItem from "./SidebarItem";
import { sidebarMenus, logoutMenu } from "./sidebarMenus";
import {
    PanelLeftClose,
    PanelLeftOpen
} from "lucide-react";

export default function Sidebar() {

    const [collapsed, setCollapsed] = useState(false);
    const { user } = useAuth();

    const role = user?.role?.toLowerCase();

    const roleMap = {

        "Admin": "admin",

        "Doctor": "doctor",

        "Clinic Staff": "staff"

    };


    const menuRole = roleMap[user.role];

    const menus = sidebarMenus[menuRole];

    return (

        <aside className={collapsed
            ? "dashboard-sidebar collapsed"
            : "dashboard-sidebar"
        }>

            {/* Header */}

            <div className="sidebar-header">

                {!collapsed && (

                    <>
                        <h2>CareSync</h2>

                        <p>Clinic Management</p>
                    </>

                )}

                <button

                    className="collapse-btn"

                    onClick={() =>
                        setCollapsed(!collapsed)
                    }

                >

                    {collapsed
                         ? <PanelLeftOpen size={18}/>
                        : <PanelLeftClose size={18}/>
                    }
                </button>

            </div>

            {/* Sections */}

            {menus.map(section => (

                <div
                    className="sidebar-section"
                    key={section.title}
                >

                    {!collapsed && (

                        <h4>

                            {section.title}

                        </h4>

                    )}

                    {section.items.map(item => (

                        <SidebarItem

                            key={item.path}

                            icon={item.icon}

                            label={item.label}

                            path={item.path}

                            collapsed={collapsed}

                        />

                    ))}

                </div>

            ))}

            {/* Footer */}

            <div className="sidebar-footer">

    {!collapsed && user && (

        <div className="sidebar-user">

            <div className="sidebar-user-name">

                {user.firstName} {user.lastName}

            </div>

            <div className="sidebar-user-role">

                {user.role}

            </div>

        </div>

    )}

        <SidebarItem
            icon={logoutMenu.icon}
            label={logoutMenu.label}
            path={logoutMenu.path}
            action={logoutMenu.action}
            collapsed={collapsed}
        />

    </div>

        </aside>

    );

}