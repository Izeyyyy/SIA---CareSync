import "../../styles/dashboard.css";
export default function ResetPasswordModal({
    user,
    onClose,
    onConfirm
}) {

    if (!user) return null;

    return (

        <div className="modal-overlay">

            <div className="reset-modal">

                <h2>
                    Reset Password
                </h2>

                <p>
                    Are you sure you want to reset the password for
                </p>

                <h3>
                    {user.firstName} {user.lastName}
                </h3>

                <div className="reset-info">

                    <strong>Defualt Password</strong>

                    <div className="password-box">
                        123456
                    </div>

                    <small>
                        The user will be required to change this password
                        immediately after logging in.
                    </small>

                </div>

                <div className="modal-buttons">

                    <button
                        onClick={onClose}
                    >
                        Cancel
                    </button>

                    <button
                        className="reset-btn"
                        onClick={onConfirm}
                    >
                        Reset Password
                    </button>

                </div>

            </div>

        </div>

    );

}