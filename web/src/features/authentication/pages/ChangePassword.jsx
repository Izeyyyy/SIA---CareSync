import { useState } from "react";
import { useNavigate } from "react-router-dom";

import FormField from "../../../components/common/FormField";
import PrimaryButton from "../../../components/common/PrimaryButton";
import { useToast } from "../../../context/ToastContext";

import { changePassword } from "../service/authService";


export default function ChangePassword(){

    const navigate = useNavigate();

    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const { showToast } = useToast();
    


    const handleSubmit = async (e) => {

        e.preventDefault();


        if(newPassword !== confirmPassword){

            showToast(
                "Passwords do not match."
            );

            return;

        }


        try {

            await changePassword(
                newPassword
            );


            showToast(
                "Password changed successfully."
            );


            setTimeout(() => {

                navigate("/login");

            }, 800);


        } catch(error){

            console.error(
                "Failed changing password:",
                error
            );


            showToast(
                "Failed changing password.",
                "error"
            );

        }

    };


    return (

        <div className="auth-container">

            <div className="auth-card">


                <h2>
                    Change Password
                </h2>


                <p>
                    Your password was reset by an administrator.
                    Please change your password before continuing.
                </p>



                <form onSubmit={handleSubmit}>


                    <FormField
                        label="New Password"
                    >

                        <input

                            type="password"

                            value={newPassword}

                            onChange={
                                (e) =>
                                setNewPassword(e.target.value)
                            }

                            required

                        />

                    </FormField>



                    <FormField
                        label="Confirm Password"
                    >

                        <input

                            type="password"

                            value={confirmPassword}

                            onChange={
                                (e) =>
                                setConfirmPassword(e.target.value)
                            }

                            required

                        />

                    </FormField>



                    <PrimaryButton
                        type="submit"
                    >

                        Update Password

                    </PrimaryButton>


                </form>


            </div>

        </div>

    );

}