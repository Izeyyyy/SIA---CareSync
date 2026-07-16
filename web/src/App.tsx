import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./features/authentication/Login";
import Register from "./features/authentication/Register";
import AdminDashboard from "./features/dashboard/AdminDashboard";
import ClinicStaffDashboard from "./features/dashboard/ClinicStaffDashboard";
import ProtectedRoute from "./components/auth/ProtectedRoute";
import AdminLayout from "./layouts/AdminLayout";
import UserManagementPage from "./features/userManagement/pages/UserManagementPage";
import DoctorManagementPage from "./features/doctorManagement/pages/DoctorManagementPage";
import ClinicStaffManagementPage from "./features/clinicStaffManagement/pages/ClinicStaffManagementPage";
import ClinicStaffLayout from "./layouts/ClinicStaffLayout";
import PatientListPage from "./features/patientManagement/pages/PatientListPage";
import RegisterPatientPage from "./features/patientManagement/pages/RegisterPatientPage";
import PatientRecordsPage from "./features/patientManagement/pages/PatientRecordsPage";
import EditPatientPage from "./features/patientManagement/pages/EditPatientPage";
import AuditLogsPage from "./features/auditLogs/pages/AuditLogsPage";
import ConsultationListPage from "./features/consultation/pages/ConsultationListPage";
import NewConsultationPage from "./features/consultation/pages/NewConsultationPage";
import EditConsultationPage from "./features/consultation/pages/EditConsultationPage";
import ConsultationDetailsPage from "./features/consultation/pages/ConsultationDetailsPage";
import DoctorLayout from "./layouts/DoctorLayout";
import DoctorDashboard from "./features/dashboard/DoctorDashboard";
import DoctorPatientListPage from "./features/doctorManagement/pages/DoctorPatientListPage";




function App() {
    return (
        <BrowserRouter>
            <Routes>

                <Route path="/" element={<Login />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route
                    path="/admin"
                    element={
                        <ProtectedRoute role="admin">
                            <AdminLayout />
                        </ProtectedRoute>
                    }
                >

                    <Route
                        path="dashboard"
                        element={<AdminDashboard />}
                    />

                    <Route
                        path="users"
                        element={<UserManagementPage />}
                    />

                    <Route
                        path="doctors"
                        element={<DoctorManagementPage />}
                    />

                    <Route
                        path="staff"
                        element={<ClinicStaffManagementPage />}
                    />

                    <Route
                        path="/admin/audit"
                        element={<AuditLogsPage />}
                    />

                </Route>

                <Route
                    path="/staff"
                    element={
                        <ProtectedRoute role="clinic staff">
                            <ClinicStaffLayout />
                        </ProtectedRoute>
                    }
                >

                    <Route
                        path="dashboard"
                        element={<ClinicStaffDashboard />}
                    />

                    <Route
                        path="patients"
                        element={<PatientListPage />}
                    />

                    <Route
                        path="patients/register"
                        element={<RegisterPatientPage />}
                    />

                    <Route
                        path="patients/:id"
                        element={<PatientRecordsPage />}
                    />

                    <Route
                        path="patients/:id/edit"
                        element={<EditPatientPage />}
                    />

                </Route>

                <Route
                    path="/doctor"
                    element={
                        <ProtectedRoute role="doctor">
                            <DoctorLayout />
                        </ProtectedRoute>
                    }
                >

                    <Route
                        path="dashboard"
                        element={<DoctorDashboard />}
                    />

                    <Route
                        path="patients"
                        element={<DoctorPatientListPage />}
                    />

                    <Route
                        path="consultations/patients/:id"
                        element={<ConsultationListPage />}
                    />

                    <Route
                        path="consultations/new/:id"
                        element={<NewConsultationPage />}
                    />

                    <Route
                        path="consultations/:id"
                        element={<ConsultationDetailsPage />}
                    />

                    <Route
                        path="consultations/edit/:id"
                        element={<EditConsultationPage />}
                    />


                </Route>

                

                </Routes>
                        </BrowserRouter>
                    );
                }

export default App;