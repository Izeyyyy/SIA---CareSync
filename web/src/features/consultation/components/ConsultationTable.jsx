import DataTable from "../../../components/common/DataTable";

export default function ConsultationTable({

    consultations,
    onView,
    onEdit,
    onDelete
}) {

    return (
        <DataTable
            columns={[

                "Date",
                "Doctor",
                "Diagnosis",
                "Actions"
            ]}

            data={consultations}

            emptyMessage="No consultations found."

            renderRow={(consultation) => (
                <tr key={consultation.id}>

                    <td>
                        {
                            new Date(
                                consultation.consultationDate
                            )
                            .toLocaleDateString()
                        }
                    </td>

                    <td>
                        {consultation.doctorName}
                    </td>

                    <td>
                        {
                            consultation.diagnosis ||
                            "No diagnosis"
                        }
                    </td>

                    <td>
                        <button
                            className="patient-action-btn"
                            onClick={() =>
                                onView(consultation)
                            }
                        >
                            View
                        </button>

                        <button
                            className="patient-action-btn"
                            onClick={() =>
                                onEdit(consultation)
                            }
                        >
                            Edit
                        </button>

                        {
                            onDelete &&

                            <button

                                className="patient-action-btn"

                                onClick={() =>
                                    onDelete(consultation)
                                }
                            >
                                Delete
                            </button>
                        }
                    </td>

                </tr>
            )}
        />
    );
}