import PrimaryButton from "./PrimaryButton";

export default function EmptyState({

    title,

    description,

    actionLabel,

    onAction

}) {

    return (

        <div className="empty-state">

            <div className="empty-state-icon">

                📋

            </div>

            <h2>{title}</h2>

            <p>{description}</p>

            {

                actionLabel && (

                    <PrimaryButton
                        onClick={onAction}
                    >

                        {actionLabel}

                    </PrimaryButton>

                )

            }

        </div>

    );

}