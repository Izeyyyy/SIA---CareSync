export default function DataTable({

    columns,
    data,
    renderRow,
    emptyMessage = "No records found."

}) {

    return (

        <div className="table-container">

            <table className="dashboard-table">

                <thead>

                    <tr>

                        {

                            columns.map(column => (

                                <th key={column}>

                                    {column}

                                </th>

                            ))

                        }

                    </tr>

                </thead>

                <tbody>

                    {

                        data.length === 0

                        ?

                        (

                            <tr>

                                <td
                                    colSpan={columns.length}
                                    className="table-empty"
                                >

                                    {emptyMessage}

                                </td>

                            </tr>

                        )

                        :

                        data.map(renderRow)

                    }

                </tbody>

            </table>

        </div>

    );

}