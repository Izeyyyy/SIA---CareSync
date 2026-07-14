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
import DashboardLayout from "./components/dashboard/DashboardLayout";
import ClinicStaffLayout from "./layouts/ClinicStaffLayout";
import PatientListPage from "./features/patientManagement/pages/PatientListPage";
import RegisterPatientPage from "./features/patientManagement/pages/RegisterPatientPage";
import PatientRecordsPage from "./features/patientManagement/pages/PatientRecordsPage";




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
                        path="records"
                        element={<PatientRecordsPage />}
                    />

                </Route>

                

                            </Routes>
                        </BrowserRouter>
                    );
                }

export default App;