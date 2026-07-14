import { useNavigate } from "react-router-dom";
import { UserRoundPlus, FolderHeart, ArrowRight } from "lucide-react";

import DashboardCard from "../../../components/dashboard/DashboardCard";

export default function QuickActions() {

    const navigate = useNavigate();

    return (

        <DashboardCard>

            <div className="quick-actions">

                <h2>Quick Actions</h2>

                <p>
                    Quickly access the most frequently used patient management tools.
                </p>

                <div className="quick-actions-grid">

                    <div
                        className="quick-action-card"
                        onClick={() => navigate("/staff/patients/register")}
                    >

                        <div className="quick-action-icon">
                            <UserRoundPlus size={24} />
                        </div>

                        <h3>Register Patient</h3>

                        <p>
                            Create a new patient profile and assign a unique patient number.
                        </p>

                        <div className="quick-action-link">

                            Open

                            <ArrowRight size={16} />

                        </div>

                    </div>

                    <div
                        className="quick-action-card"
                        onClick={() => navigate("/staff/patients")}
                    >

                        <div className="quick-action-icon">
                            <FolderHeart size={24} />
                        </div>

                        <h3>Patient Directory</h3>

                        <p>
                            View, search, and manage all registered patient records.
                        </p>

                        <div className="quick-action-link">

                            Open

                            <ArrowRight size={16} />

                        </div>

                    </div>

                </div>

            </div>

        </DashboardCard>

    );

}