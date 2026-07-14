export default function SecondaryButton({

    children,

    ...props

}) {

    return (

        <button

            className="secondary-btn"

            {...props}

        >

            {children}

        </button>

    );

}