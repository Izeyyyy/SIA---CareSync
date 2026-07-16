import {
    LayoutDashboard,
    Users,
    UserCog,
    ClipboardList,
    Settings,
    FolderHeart,
    LogOut,
    Stethoscope,
    CalendarDays,
    Clock3
} from "lucide-react";

export const sidebarMenus = {

    admin: [

        {
            title: "MAIN",

            items: [

                {
                    label: "Dashboard",
                    path: "/admin/dashboard",
                    icon: LayoutDashboard
                }

            ]

        },

        {

            title: "MANAGEMENT",

            items: [

                {
                    label: "Users",
                    path: "/admin/users",
                    icon: Users
                },

                {
                    label: "Doctors",
                    path: "/admin/doctors",
                    icon: Stethoscope
                },

                {
                    label: "Clinic Staff",
                    path: "/admin/staff",
                    icon: UserCog
                }

            ]

        },

        {

            title: "SYSTEM",

            items: [

                {
                    label: "Audit Logs",
                    path: "/admin/audit",
                    icon: ClipboardList
                },

                {
                    label: "Settings",
                    path: "/admin/settings",
                    icon: Settings
                }

            ]

        }

    ],



    doctor: [

        {

            title: "MAIN",

            items: [

                {
                    label: "Dashboard",
                    path: "/doctor/dashboard",
                    icon: LayoutDashboard
                }

            ]

        },

        {

            title: "PATIENT CARE",

            items: [

                {
                    label: "My Patients",
                    path: "/doctor/patients",
                    icon: Users
                }


            ]

        }

    ],



    staff: [

    {

        title: "MAIN",

        items: [

            {
                label: "Dashboard",
                path: "/staff/dashboard",
                icon: LayoutDashboard
            }

        ]

    },


    {

        title: "PATIENT MANAGEMENT",

        items: [

            {
                label: "Patients",
                path: "/staff/patients",
                icon: Users
            },


            {
                label: "Register Patient",
                path: "/staff/patients/register",
                icon: ClipboardList
            },

        ]

    }

]
};



export const logoutMenu = {

    label: "Logout",

    path: "/login",

    icon: LogOut,

    action: "logout"

};