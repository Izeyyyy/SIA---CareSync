import { useEffect, useState } from "react";
import DashboardCard from "../../../components/dashboard/DashboardCard";
import { getMyActivity } from "../services/auditService";
import DataTable from "../../../components/common/DataTable";

export default function MyActivityPage(){
    const [logs,setLogs]=useState([]);
    useEffect(()=>{
        loadLogs();
    },[]);
    async function loadLogs(){
        try{
            const data = await getMyActivity();
            setLogs(data);
        }

        catch(err){
            console.error(err);

        }
    }

    return(
        <DashboardCard title="My Activity">
            <DataTable

    columns={[
        "Date",
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

            <td className="audit-description">
                {log.description}
            </td>

        </tr>

    )}

 />
        </DashboardCard>
    );
}