import { useNavigate } from "react-router-dom";

import DataTable from "../../../components/common/DataTable";


export default function PatientTable({ patients }) {

    const navigate = useNavigate();


    const calculateAge = (birthDate) => {

        const today = new Date();
        const birth = new Date(birthDate);


        let age =
            today.getFullYear() -
            birth.getFullYear();


        const monthDifference =
            today.getMonth() -
            birth.getMonth();


        if (

            monthDifference < 0 ||

            (
                monthDifference === 0 &&
                today.getDate() < birth.getDate()
            )

        ) {

            age--;

        }


        return age;

    };


    const formatDate = (date) => {

        if (!date) return "N/A";


        return new Date(date)
            .toLocaleDateString();

    };


    return (

        <DataTable

            columns={[

                "Patient No.",

                "Name",

                "Birth Date",

                "Age",

                "Gender",

                "Contact",

                "Date Registered",

                "Actions"

            ]}


            data={patients}


            emptyMessage="No patients found."


            renderRow={(patient) => (

                <tr key={patient.id}>


                    <td>

                        {patient.patientNumber}

                    </td>



                    <td>

                        {patient.lastName},{" "}

                        {patient.firstName}{" "}

                        {patient.middleInitial}.

                    </td>



                    <td>

                        {formatDate(
                            patient.birthDate
                        )}

                    </td>



                    <td>

                        {calculateAge(
                            patient.birthDate
                        )} years old

                    </td>



                    <td>

                        {patient.gender}

                    </td>



                    <td>

                        {patient.contactNumber}

                    </td>



                    <td>

                        {formatDate(
                            patient.dateRegistered
                        )}

                    </td>



                    <td>


                        <button

                            className="patient-action-btn"

                            onClick={() =>
                                navigate(
                                    `/staff/patients/${patient.id}`
                                )
                            }

                        >

                            View

                        </button>



                        <button

                            className="patient-action-btn"

                            onClick={() =>
                                navigate(
                                    `/staff/patients/${patient.id}/edit`
                                )
                            }

                        >

                            Edit

                        </button>


                    </td>


                </tr>

            )}

        />

    );

}