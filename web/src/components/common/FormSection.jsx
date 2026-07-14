export default function FormSection({

    title,

    children

}) {

    return (

        <section className="form-section">

            <h3>

                {title}

            </h3>

            <div className="form-section-content">

                {children}

            </div>

        </section>

    );

}