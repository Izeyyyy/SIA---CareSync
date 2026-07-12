import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";

export default function SidebarItem({
    icon: Icon,
    label,
    path,
    action,
    collapsed
}) {

    const navigate = useNavigate();
    const { logout } = useAuth();

    const handleClick = (e) => {

        if (label === "Logout") {

            e.preventDefault();

            logout();

            navigate("/login");

        }

    };

    return (

        <NavLink

            to={path}

            onClick={handleClick}

            className={({ isActive }) =>
                isActive
                    ? "sidebar-item active"
                    : "sidebar-item"
            }

        >

            <Icon
                size={20}
                className="sidebar-icon"
            />

            {!collapsed && <span>{label}</span>}

        </NavLink>

    );

}