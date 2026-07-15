import { useEffect, useState } from "react";

import DashboardLayout from "../../../components/dashboard/DashboardLayout";
import DashboardCard from "../../../components/dashboard/DashboardCard";

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

                <table className="data-table">

                    <thead>

                        <tr>

                            <th>Date & Time</th>
                            <th>User</th>
                            <th>Role</th>
                            <th>Action</th>
                            <th>Module</th>
                            <th>Description</th>

                        </tr>

                    </thead>

                    <tbody>

                        {logs.map(log => (

                            <tr key={log.id}>

                                <td>
                                    {new Date(log.createdAt)
                                        .toLocaleString()}
                                </td>

                                <td>{log.username}</td>

                                <td>{log.role}</td>

                                <td>{log.action}</td>

                                <td>{log.module}</td>

                                <td>{log.description}</td>

                            </tr>

                        ))}

                    </tbody>

                </table>

            </DashboardCard>


    );

}