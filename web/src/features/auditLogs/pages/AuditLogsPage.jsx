import { useEffect, useState } from "react";

import DashboardLayout from "../../../components/dashboard/DashboardLayout";
import DashboardCard from "../../../components/dashboard/DashboardCard";
import DataTable from "../../../components/common/DataTable";

import { getAuditLogs } from "../services/auditService";

export default function AuditLogsPage() {

    const [logs, setLogs] = useState([]);

    useEffect(() => {
        loadLogs();
    }, []);

    async function loadLogs() {

        try {

            const data = await getAuditLogs();
            setLogs(data);

        } catch (err) {

            console.error(err);

        }

    }

    return (


            <DashboardCard title="Audit Logs">

                <DataTable

    columns={[
        "Date",
        "User",
        "Role",
        "Action",
        "Module",
        "Description"
    ]}

    data={logs}

    renderRow={(log)=>(

        <tr key={log.id}>

            <td>

                {new Date(log.createdAt).toLocaleString("en-PH",{

                    year:"numeric",
                    month:"short",
                    day:"numeric",
                    hour:"numeric",
                    minute:"2-digit"

                })}

            </td>

            <td>{log.username}</td>

            <td>{log.role}</td>

            <td>

                <span className={`audit-action ${log.action.toLowerCase()}`}>

                    {log.action}

                </span>

            </td>

            <td>

                <span className="audit-module">

                    {log.module}

                </span>

            </td>

            <td>{log.description}</td>

        </tr>

    )}

 />

            </DashboardCard>


    );

}