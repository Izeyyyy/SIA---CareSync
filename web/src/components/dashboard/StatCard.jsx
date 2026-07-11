export default function StatCard({ title, value }) {

    return (
        <div className="dashboard-card">

            <p className="stat-title">
                {title}
            </p>


            <h2 className="stat-value">
                {value}
            </h2>

        </div>
    );
}